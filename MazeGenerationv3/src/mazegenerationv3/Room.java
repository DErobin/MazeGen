/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazegenerationv3;

import java.awt.Rectangle;

/**
 *
 * @author Robin
 */
public class Room 
{
    public final Rectangle area;
    
    public Room(int x, int y, int w, int h)
    {
        area = new Rectangle(x, y, w, h);
    }
    
    public boolean OverLapsWith(Room roomin)
    {
        Rectangle r = roomin.area;
        return area.x < r.x + r.width && area.x + area.width > r.x && area.y < r.y + r.height && area.y + area.height > r.y;
    }
}
