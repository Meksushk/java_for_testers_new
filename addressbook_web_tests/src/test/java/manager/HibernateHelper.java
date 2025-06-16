package manager;

import common.CommonFunctions;
import manager.hbm.AddressInGroups;
import manager.hbm.AddressRecord;
import manager.hbm.GroupRecord;
import model.AddressData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

import static tests.TestBase.app;

public class HibernateHelper extends HelperBase {

    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory = new Configuration()
                .addAnnotatedClass(AddressRecord.class)
                .addAnnotatedClass(GroupRecord.class)
                .addAnnotatedClass(AddressInGroups.class)
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                .buildSessionFactory();
    }

    static List<GroupData> convertList(List<GroupRecord> records){
        List<GroupData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convert(record));
        }
        return result;
    }

    static List<AddressData> convertAddressList(List<AddressRecord> records){
        List<AddressData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convert(record));
        }
        return result;
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static AddressData convert(AddressRecord record) {
        return new AddressData("" + record.id, record.firstname, record.lastname, record.mobile);
    }

    private static GroupRecord convert(GroupData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    private static AddressRecord convert(AddressData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new AddressRecord(Integer.parseInt(id), data.firstname(), data.lastname(), data.mobile());
    }

    public List<GroupData> getGroupList() {
        return convertList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    public List<AddressData> getAddressList() {
        return convertAddressList(sessionFactory.fromSession(session -> {
            return session.createQuery("from AddressRecord", AddressRecord.class).list();
        }));
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public long getAddressCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from AddressRecord", Long.class).getSingleResult();
        });
    }

    public long getAddressInGroupsCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from AddressInGroups", Long.class).getSingleResult();
        });
    }

    public boolean isAddressInGroup(int addressId, int groupId) {
        return sessionFactory.fromSession(session -> {
            var count = session.createQuery("select count (*) from AddressInGroups aig where aig.id = :addressId and aig.group_id = :groupId", Long.class)
                    .setParameter("addressId", addressId)
                    .setParameter("groupId", groupId)
                    .getSingleResult();
            return count > 0;
        });
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupData));
            session.getTransaction().commit();
        });
    }

    public void createAddress(AddressData addressData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(addressData));
            session.getTransaction().commit();
        });
    }

    public List<AddressData> getAddressesInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertAddressList(session.get(GroupRecord.class, group.id()).addresses);
        });
    }


    public List<AddressInGroups> findAddressGroupPairToAdd(List<AddressData> addresses, List<GroupData> groups) {
        List<AddressInGroups> pair = new ArrayList<>();
        int counter = 0;
        for (AddressData address : addresses) {
            int indexA = Integer.parseInt(address.id());
            if (counter == addresses.size() - 1) {
                app.hbm().createGroup(new GroupData("", "" + CommonFunctions.randomString(10), "", ""));
                groups = new ArrayList<>();
            }
            for (GroupData group : groups) {
                int indexG = Integer.parseInt(group.id());
                if (!app.hbm().isAddressInGroup(indexA, indexG)) {
                    pair.add(new AddressInGroups(indexA,indexG));
                    return pair;
                }
            }
            counter ++;
        }
        return pair;
    }

    public AddressData findAddressById(String id, List<AddressData> addresses) {
        for (AddressData address : addresses) {
            if (address.id().equals(id)) {
                return address;
            }
        }
        return null;
    }

    public GroupData findGroupById(String id, List<GroupData> groups) {
        for (GroupData group : groups) {
            if (group.id().equals(id)) {
                return group;
            }
        }
        return null;
    }
}
