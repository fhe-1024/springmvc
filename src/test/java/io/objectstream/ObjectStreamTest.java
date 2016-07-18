package io.objectstream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectStreamTest {
	public static void main(String[] args) {
		try {

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("E:/1.txt")));
			out.writeInt(11);
			out.writeObject(new Student("1", "fhe", 12));
			out.writeObject(new Student("2", "mine", 18));
			out.flush();
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("E:/1.txt")));
			System.out.println(in.readInt());
			Student student1 = (Student) in.readObject();
			System.out.println(student1.getId() + ":" + student1.getName() + " :" + student1.getAge());
			Student student2 = (Student) in.readObject();
			System.out.println(student2.getId() + ":" + student2.getName() + " :" + student2.getAge());
			in.close();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
