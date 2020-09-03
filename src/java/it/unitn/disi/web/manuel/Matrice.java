package it.unitn.disi.web.manuel;

import java.util.Random;



/**
 *
 * @author manuel
 */
public class Matrice {
    Cella[][] mat;
    final int NELEM = 9;
    final int NBOMBE = 10;
    
    public Matrice(){
        mat = new Cella[NELEM][NELEM];
        for(int i=0; i<NELEM; i++)  mat[i] = new Cella[NELEM];
        
        for(int i=0; i<NELEM; i++)
            for(int j=0; j<NELEM; j++)
                mat[i][j] = new Cella();
        
        popolaMatrice();
    }
    
    class Cella{
        public int val;
        Cella(){
            this.val = 0;
        }
        Cella( int v){
            this.val = v;
        }
        public void setbomba(){
            this.val = -1;
        }
        public boolean isbomba(){
            return this.val == -1;
        }
        public void incr(){
            this.val++;
        }
    }

    public int getValue(int i, int j){
        return mat[i][j].val;
    }
    
    public final void popolaMatrice(){
        Random rand = new Random();
        int bombeCreate = 0;
        
        while( bombeCreate < NBOMBE){
            int i = rand.nextInt(NELEM);
            int j = rand.nextInt(NELEM);
            
            if( !mat[i][j].isbomba()){
                mat[i][j].setbomba();
                incrementaVicini( i, j);
                bombeCreate++;
            }
        }
    }
    
    private void incrementaVicini( int i, int j){
        //ALTO SX  i-1, j-1
        if( i-1 > 0 && j-1 > 0 && !mat[i-1][j-1].isbomba()){
            mat[i-1][j-1].incr();
        }
        //ALTO CENTRO  i-1, j
        if( i-1 > 0 && !mat[i-1][j].isbomba()){
            mat[i-1][j].incr();
        }
        //ALTO DX  i-1, j+1
        if( i-1 > 0 && j+1 < NELEM && !mat[i-1][j+1].isbomba()){
            mat[i-1][j+1].incr();
        }
        //CENTRO DX  i, j+1
        if( j+1 < NELEM && !mat[i][j+1].isbomba()){
            mat[i][j+1].incr();
        }
        //BASSO DX  i+1, j+1
        if( i+1 < NELEM && j+1 < NELEM && !mat[i+1][j+1].isbomba()){
            mat[i+1][j+1].incr();
        }
        //BASSO CENTRO  i+1, j
        if( i+1 < NELEM && !mat[i+1][j].isbomba()){
            mat[i+1][j].incr();
        }
        //BASSO SX  i+1, j-1
        if( i+1 < NELEM && j-1 > 0 && !mat[i+1][j-1].isbomba()){
            mat[i+1][j-1].incr();
        }
        //CENTRO SX  i, j-1
        if( j-1 > 0 && !mat[i][j-1].isbomba()){
            mat[i][j-1].incr();
        }
    }
    
    public void resetMatrice(){
        this.mat = null;
        this.mat = new Cella[NELEM][NELEM];
        for(int i=0; i<NELEM; i++)  mat[i] = new Cella[NELEM];
        
        for(int i=0; i<NELEM; i++)
            for(int j=0; j<NELEM; j++)
                mat[i][j] = new Cella();
        
        popolaMatrice();
    }
}
