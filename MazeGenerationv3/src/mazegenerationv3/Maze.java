/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazegenerationv3;

import java.util.*;
import java.awt.Rectangle;
/**
 *
 * @author Robin
 */
public class Maze 
{
    //Maze grid:
    private final Block grid[][];
    
    //Sizes:
    private final int gridSize;
    private Random rand;

    //Rooms:
    private final int roomSize;
    private final int roomTries;
    private ArrayList<Room> finRooms;
    
    public Maze(int gridsize, int roomsize, int roomtries)
    {
        this.gridSize = gridsize;
        this.grid = new Block[gridsize][gridsize]; //Y-X
        this.rand = new Random();
        this.roomSize = roomsize;
        this.roomTries = roomtries;
        this.finRooms = new ArrayList<Room>();
        
        //Starting off with nothing
        for(Block[] row : grid)
        {
            Arrays.fill(row, Block.SOLID);
        }
        for(int i=0; i<roomTries; i++)
        {
            Room r = generateRoom();
            System.out.println("Room genned. X: " + r.area.x + " Y: " + r.area.y + " X2: " + (r.area.x+r.area.width) + " Y2: " + (r.area.y+r.area.height));
            boolean overlap = false;
            for(Room s : finRooms)
            {
                if(r.OverLapsWith(s))
                {
                    System.out.println("Room overlaps");
                    overlap = true;
                }
            }
            
            if(overlap)
                continue;
            else
            {
                finRooms.add(r);
                //System.out.println("Room drawed");
                drawRoom(r);
            }
        }
    }
    private void drawRoom(Room r)
    {
        Rectangle rec = r.area;
        System.out.println("Starting draw");
        for(int y=rec.y; y<rec.y+rec.height; y++)
        {
            for(int x= rec.x; x<rec.x+rec.width; x++)
            {
                System.out.println("Grid X:" + x + " Y:" + y);
                grid[y][x]= Block.OPEN;
            }
        }
    }
    
    public void printMaze()
    {
        for(int y=0; y<gridSize; y++)
        {
            String line = "";
            for(int x=0; x<gridSize; x++)
            {
                if(grid[y][x] == Block.SOLID)
                    line += "X";
                if(grid[y][x] == Block.OPEN)
                    line += "O";
            }
            System.out.println(line);
        }
        
    }
    
    private Room generateRoom()
    {
        int width= newRand(roomSize);
        int height = newRand(roomSize);
        System.out.println("W:" + width + " H:" + height);
        Room r = new Room(1+newRand(gridSize-width-2), 1+newRand(gridSize-height-2), 2+width, 2+height);
        return r;
    }
    
    private int newRand(int bound)
    {
        return rand.nextInt(bound);
    }
}
