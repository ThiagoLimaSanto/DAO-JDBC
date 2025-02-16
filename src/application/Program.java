package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

		System.out.println("=== TEST 1: findById =======");
		Department dep = departmentDao.findById(1);
		System.out.println(dep);

		System.out.println("\n=== TEST 2: findAll =======");
		List<Department> list = departmentDao.findAll();
		for (Department d : list) {
			System.out.println(d);
		}

		System.out.println("\n=== TEST 3: insert =======");
		Department newDepartment = new Department(null, "Music");
		departmentDao.insert(newDepartment);
		System.out.println("Insert successful!");

		System.out.println("\n=== TEST 4: update =======");
		Department dep2 = departmentDao.findById(1);
		dep2.setName("Food");
		departmentDao.update(dep2);
		System.out.println("Update completed");

		System.out.println("\n=== TEST 5: delete =======");
		System.out.print("Enter id for delete test: ");
		int id = scan.nextInt();
		departmentDao.deleteByID(id);
		System.out.println("Delete completed");



		System.out.println("========================================================================");


		
		SellerDao sellerDao = DaoFactory.createSellerDao();

		System.out.println("=== TEST 1: Seller findByID ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);

		System.out.println();

		System.out.println("=== TEST 2: Seller findByDepartment ===");
		Department department = new Department(2, null);
		List<Seller> list2 = sellerDao.findByDepartment(department);
		for (Seller obj : list2) {
			System.out.println(obj);
		}

		System.out.println();

		System.out.println("=== TEST 3: Seller findAll ===");
		list2 = sellerDao.findAll();
		for (Seller obj : list2) {
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
		id = scan.nextInt();
		sellerDao.deleteByID(id);
		System.out.println("Delete completed!");

		scan.close();
	}
}