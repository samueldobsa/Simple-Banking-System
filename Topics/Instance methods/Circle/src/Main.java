class Circle {

    double radius;

    // write methods here
    public double getLength(){
        double length = 2 * Math.PI * radius;
        return length;
    }
    public double getArea(){
        double area = Math.PI * radius * radius;
        return area;
    }
}