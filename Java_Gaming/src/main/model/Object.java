package main.model;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Object {
	Position position;
	Position size;
	ArrayList<Image> images;
	int p; //iterator
	
	//intialise objects
	public Object(Position cornor_1, Position size) {
		this.position = cornor_1;
		this.size = size;
		images = new ArrayList<Image>();
		p = 0;
	}
	public Object(Position cornor_1, Image im) {
		this.position = cornor_1;
		images = new ArrayList<Image>();
		images.add(im);
		this.size = new Position(im.getWidth()-1, im.getHeight()-1);
		p = 0;
	}
	public Object(Position cornor_1, ArrayList<Image> images) {
		this.position = cornor_1;
		this.images = new ArrayList<Image>();
		this.images.addAll(images);
		this.size = new Position(images.get(0).getWidth()-1, images.get(0).getHeight()-1);
		p = 0;
	}
	public Object(Position cornor_1, Position size, ArrayList<Image> images) {
		this.position = cornor_1;
		this.size = size;
		this.images = new ArrayList<Image>();
		this.images.addAll(images);
		p = 0;
	}
	public Object(Position cornor_1, Position size, Image im) {
		this.position = cornor_1;
		this.size = size;
		images = new ArrayList<Image>();
		images.add(im);
		p = 0;
	}
	//basic getter and setter 
	//adder and remover for arrays
	public Position getSize() { return size; }
	public void setSize(Position size) {this.size = size;}
	public void setSize(int x, int y) {this.size = new Position(x, y);}
	public Position getPosition() {return position;}
	public void setPosition(Position pos) {this.position = pos;}
	public void setPosition(double x, double y) {this.position = new Position(x, y);}
	public Image getCurrentImage() {return images.get(p);}
	public void setIterator(int p) { this.p = p; }
	public void incrementIterator() { p++; if (p >= images.size()) p = 0;}
	public void addImage (Image im) {images.add(im);}
	public void addImage (ArrayList<Image> im) {images.addAll(im);}
	public void removeImage(int index) {images.remove(index);}
	
	//check whether position is inside the object or not
	public boolean isInsideObject(double x, double y) {
		if (x >= position.getX() && y >= position.getY() && x <= (position.getX() + size.getX()) && y <= (position.getY() + size.getY()))
			return true;
		else return false;
	}
	
	//check whether object is collide another object
	public boolean isCollideObject(Object object) {
		if (isInsideObject(object.getPosition().getX(),object.getPosition().getY()) || 
				isInsideObject(object.getPosition().getX() + object.getSize().getX(),object.getPosition().getY()) ||
						isInsideObject(object.getPosition().getX() + object.getSize().getX(),object.getPosition().getY() + object.getSize().getY()) ||
								isInsideObject(object.getPosition().getX(),object.getPosition().getY() + object.getSize().getY()))
			return true;
		else
			return false;
	}
	//check whether object collide top side with another object
	public boolean isCollideTop(Object object) {
		if (isInsideObject(object.getPosition().getX(),object.getPosition().getY()) || 
				isInsideObject(object.getPosition().getX() + object.getSize().getX(),object.getPosition().getY()))
			return true;
		else
			return false;
	}
	//check whether object collide bottom side with another object
	public boolean isCollideBottom(Object object) {
		if (isInsideObject(object.getPosition().getX() + object.getSize().getX(),object.getPosition().getY() + object.getSize().getY()) ||
				isInsideObject(object.getPosition().getX(),object.getPosition().getY() + object.getSize().getY()))
			return true;
		else
			return false;
	}
	//check whether object collide left side with another object
	public boolean isCollideLeft(Object object) {
		if (isInsideObject(object.getPosition().getX(),object.getPosition().getY()) || 
				isInsideObject(object.getPosition().getX(),object.getPosition().getY() + object.getSize().getY()))
			return true;
		else
			return false;
	}
	//check whether object collide right side with another object
	public boolean isCollideRight(Object object) {
		if (isInsideObject(object.getPosition().getX() + object.getSize().getX(),object.getPosition().getY()) ||
				isInsideObject(object.getPosition().getX() + object.getSize().getX(),object.getPosition().getY() + object.getSize().getY()))
			return true;
		else
			return false;
	}
	

	
}
