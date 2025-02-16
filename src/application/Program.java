package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TEST 1: Seller findByID ===");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println();

        System.out.println("=== TEST 2: Seller findByDepartment ===");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);
        for (Seller obj : list) {
            System.out.println(obj);
        }

        System.out.println();

        System.out.println("=== TEST 3: Seller findAll ===");
        list = sellerDao.findAll();
        for (Seller obj : list) {
            System.out.println(obj);
        }

        System.out.println();

        System.out.println("=== TEST 4: Seller insert ===");
        sellerDao.insert(new Seller(null, "Greg", "Greg@gmail.com", new Date(), 4000.00, department));
        System.out.println("Insert successful!");

        System.out.println();

        System.out.println("=== TEST 5: Seller update ===");
        seller = sellerDao.findById(1);
        seller.setName("Martha Waine");
        sellerDao.update(seller);
        System.out.println("Update completed!");

        System.out.println();

        System.out.println("=== TEST 6: Seller delete ===");
        int id = scan.nextInt();
        sellerDao.deleteByID(id);
        System.out.println("Delete completed!");

        scan.close();
    }
}
