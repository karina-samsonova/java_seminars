package com.company;
public class Complex {
    private double real;
    private double imag;
    public Complex(double real, double imag){
        this.real = real;
        this.imag = imag;
    }
    public Complex(double real){
        this.real = real;
        this.imag = 0;
    }
    public Complex set(double real, double imag){
        this.real = real;
        this.imag = imag;
        return this;
    }
    public Complex add(final Complex other){
        Complex res = new Complex(0.0, 0.0);
        res.real = this.real + other.real;
        res.imag = this.imag + other.imag;
        return(res);
    }
    public Complex sub(final Complex other){
        Complex res = new Complex(0.0, 0.0);
        res.real = this.real - other.real;
        res.imag = this.imag - other.imag;
        return(res);
    }
    public Complex mul(final Complex other){
        Complex res = new Complex(0.0, 0.0);
        res.real = this.real * other.real - this.imag * other.imag;
        res.imag = this.real * other.imag + this.imag * other.real;
        return(res);
    }
    public Complex div(final Complex other) throws ArithmeticException{
        if (other.real == 0 && other.imag == 0){
            throw new ArithmeticException("invalid value");
        }
        Complex res = new Complex(0.0, 0.0);
        res.real = (this.real * other.real + this.imag * other.imag)/(other.real * other.real + other.imag * other.imag);
        res.imag = (this.imag * other.real - this.real * other.imag)/(other.real * other.real + other.imag * other.imag);
        return(res);
    }
    public final double mod(){
        return Math.sqrt(this.real * this.real + this.imag * this.imag);
    }
    public final double arg(){
        return Math.acos(this.real / this.mod());
    }
    public final String alg(){
        if (this.imag > 0){
            return this.real + "+" + this.imag + "i";
        }
        else if (this.imag < 0){
            return this.real + this.imag + "i";
        }
        else{
            return this.real + "";
        }
    }
    public final String trig()
    {
        double fi = this.arg();
        return this.mod() + " * (cos(" + fi + ") + i * sin(" + fi + "))";
    }
}
