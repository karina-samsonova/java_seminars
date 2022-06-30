package com.company;
public class Matrix {
    private int m;
    private int n;
    private Complex[][] data;
    public Matrix(Complex[][] data) {
        this.m = data.length;
        this.n = data[0].length;
        this.data = new Complex[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                this.data[i][j] = data[i][j];
    }
    public Matrix(final int n, final int m)
    {
        this.n = n;
        this.m = m;
        this.data = new Complex[n][m];
        for (int i = 0; i < n; ++i){
            for (int j = 0; j < m; ++j){
                this.data[i][j] = new Complex(0);
            }
        }
    }
    public Matrix(final Matrix other){
        this.n = other.n;
        this.m = other.m;
        this.data = new Complex[other.n][other.m];
        for (int i = 0; i < n; ++i){
            for (int j = 0; j < m; ++j){
                this.data[i][j] = new Complex(0);
                this.data[i][j] = other.data[i][j];
            }
        }
    }

    public void set(final int i, final int j, Complex elem){
        this.data[i][j] = elem;
    }
    public final void print(){
        for (int i = 0; i < this.n; ++i){
            for (int j = 0; j < this.m; ++j){
                System.out.print(this.data[i][j].alg() + " ");
            }
            System.out.println();
        }
    }
    public Matrix add(final Matrix other){
        if (this.n != other.n || this.m != other.m){
            return this;
        }
        Matrix res = new Matrix(this);
        for (int i = 0; i < res.m; i++)
            for (int j = 0; j < res.n; j++)
                res.data[i][j] = res.data[i][j].add(other.data[i][j]);
        return res;
    }
    public Matrix mul(final Matrix other){
        if (this.m != other.n){
            return this;
        }
        Matrix temp = new Matrix(this.n, other.m);
        for (int i = 0; i < temp.n; ++i){
            for (int j = 0; j < temp.m; ++j){
                Complex sum = new Complex(0);
                for (int k = 0; k < this.m; ++k)
                {
                    sum = sum.add(this.data[i][k].mul(other.data[k][j]));
                }
                temp.data[i][j] = sum;
            }
        }
        return temp;
    }
    public Matrix T(){
        Matrix res = new Matrix(this);
        for (int i = 0; i < this.m; i++)
            for (int j = 0; j < this.n; j++)
                res.data[j][i] = this.data[i][j];
        return res;
    }
    private void subMat(Matrix mat, Matrix temp, int p, int q, int _n)
    {
        int i = 0;
        int j = 0;
        for (int row = 0; row < _n; ++row){
            for (int col = 0; col < _n; ++col){
                if (row != p && col != q){
                    temp.set(i, j++, mat.data[row][col]);
                    if (j == _n - 1){
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }
    public final Complex det(Matrix mat, int l){
        if (l == 1){
            return mat.data[0][0];
        }
        if (l == 2){
            return mat.data[0][0].mul(mat.data[1][1]).sub(mat.data[0][1].mul(mat.data[1][0]));
        }
        /*Matrix temp = new Matrix(l - 1, l - 1);
        for (int k = 0; k < l; ++k){
            for (int i = 0; i < l - 1; i++){
                int jk = 0;
                for (int j = 0; j < l - 1; j++){
                    if (jk == k) {
                        jk++;
                    }
                    temp.data[i][j] = mat.data[i+1][jk];
                }
            }
            System.out.println(d.alg());
            d = d.add(det(temp, l - 1).mul(mat.data[0][k].mul(new Complex(Math.pow(-1, k)))));
        }*/
        Complex d = new Complex(0);
        Matrix temp = new Matrix(l, l);
        int sign = 1;
        for (int i = 0; i < l; ++i)
        {
            subMat(mat, temp, 0, i, l);
            d = d.add(mat.data[0][i].mul(det(temp, l - 1).mul(new Complex(sign))));
            sign *= -1;
        }
        return d;
    }
    public Complex getDet(){
        if (this.m != this.n){
            return new Complex(0);
        }
        return det(this, this.m);
    }
}
