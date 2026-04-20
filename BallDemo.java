import java.awt.Color;
import java.util.ArrayList;
/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 * 
 * this program creates 5-30 boxball objects within a box then animated them
 * each has a randome color, velocity, trajectory, and starting position
 * 
 * @author Michael Kölling (mik)
 * @author David J. Barnes
 * 
 * @author Federico Cadavid Rojas
 * @version 2026.04.19
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private Box box;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     * 
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
        box=new Box (100,100,500,400, myCanvas);
        box.draw();
        
        Box box2 = new Box(myCanvas);
        box2.draw();
        
    }

    /**
     * boxBounce - simulate 5-50 balls bouncing within a box
     * 
     * @param numOfBalls number of balls to simulate bouncing, clamped between 5-50. 
     */
    public void boxBounce(int numBalls)
    {
        // you must implement this
        //random number of balls
        if(numBalls < 5) numBalls = 5;
        if(numBalls > 30) numBalls = 30;
        
        ArrayList<BoxBall> balls = new ArrayList<>();
        //drawin each
        for(int i = 0; i < numBalls; i++)
        {
                
            //ensuring ball is inside box
            int diameter = 15;
            //basically adding the leftwall then randomizer*width of box
            int x = box.getLeftWall()+(int)(Math.random()*(box.getRightWall() - box.getLeftWall()-diameter));
            int y = box.getTopWall()+(int)(Math.random()*(box.getBottomWall() - box.getTopWall()-diameter));
            //set colors such that they are not too light and hard to see
            Color c = new Color(50+(int)(Math.random()*180), 
                                50+(int)(Math.random()*180), 
                                50+(int)(Math.random()*180) );
                
            //put it all togetha n add to list
            BoxBall ball = new BoxBall(x, y, diameter, c, box, myCanvas);
            balls.add(ball);
            ball.draw();
             
        }
        
        while(true)
        {
            myCanvas.wait(50);
            
            for(int i = 0; i < balls.size(); i++)
            {
                balls.get(i).move();
            }
        }
        
    }
    
    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);

        // create and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
}
