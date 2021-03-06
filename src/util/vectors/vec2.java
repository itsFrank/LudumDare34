package util.vectors;

/**
 * Created by Francis O'Brien - 1/7/2015 - 2:25 AM
 *
 * Basic vector functionality for 2d floating point vectors
 *
 * Be careful when assigning a vector to another (i.e vec1 = vec 2)
 * Some portions of the code depend on shallow copies and use these vectors as pointers (see Box class) so that the values within both instances change together
 * Assigning a vector that is being "pointed" to to another vector, will break the link
 */

public class vec2 {

    public float x, y;

    /// Default constructor initializes values to zero
    public vec2(){
        x = 0;
        y = 0;
    }

    /// Parameter constructor
    public vec2(float x, float y){
        this.x = x;
        this.y = y;
    }

    /// Vector constructor
    public vec2(vec2 vec){
        x = vec.x;
        y = vec.y;
    }

    public vec2 clone(){
        return new vec2(x, y);
    }

    /// Returns the length of the vector
    public float length() {
        return (float) Math.sqrt((x*x) + (y*y));
    }

    /// Normalizes the vector
    public void normalize(){
        float length = length();
        x /= length;
        y /= length;
    }

    /// Returns angle in radians (range: -Pi to Pi)
    public float angle() {
        if (x > 0){
                return (float) Math.atan(y / x);
        } else{
            return (float) (Math.atan(y / x) + (y * Math.PI));

        }
    }

    /// Return sum of two vectors
    public vec2 add(vec2 vec){
        return new vec2(x + vec.x, y + vec.y);
    }

    /// return difference of two vectors
    public vec2 diff(vec2 vec){
        return new vec2(x - vec.x, y - vec.y);
    }

    /// return the vector scaled by a factor
    public vec2 mult(float factor){
        return new vec2(x * factor, y * factor);
    }

    /// Returns the distance between two points
    public float distance(vec2 p2){
        return diff(p2).length();
    }

    /// Add the value of a vector to this instance
    public void addSelf(vec2 vec){
        x += vec.x;
        y += vec.y;
    }

    /// Set the value of a vector to this instance
    public void setSelf(vec2 vec){
        x = vec.x;
        y = vec.y;
    }

    /// Add the value of a vector to this instance
    public void multSelf(float value){
        x *= value;
        y *= value;
    }

    public float getDirection(){
        if (y == 0){
            if (x >= 0) return 0;
            else return 180;
        }

        return (float) Math.toDegrees(Math.atan2(x, -y));
    }

    public float getAngle(vec2 point){
        vec2 vector = new vec2(point.x - x, point.y - y);
        return vector.getDirection();
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public vec2 difference(vec2 other) {
        return new vec2(x - other.x, y - other.y);
    }

    public boolean equals(vec2 other){
        return ((x == other.x) && (y == other.y));
    }
}
