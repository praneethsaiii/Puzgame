//*******PUZZLE GAME**********//
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/*
<applet code="Puzzle1" width=300 height=250>
</applet>
*/
class node
{
//random number generation
int minValue = 0;
int maxValue = 8;
int numInts = 9;
int range = maxValue - minValue;
Random r = new Random();
int[] randomInts = new int[numInts];
int nextRandom;
int count=0,s=0,value=0;
int randomize()
{
for (int  i= 0;i<numInts; i++)
{
nextRandom = r.nextInt(range + 1) + minValue;
randomInts[i] = nextRandom;
//checking for repetition
for (int j = 0; j < i; j++)
{
if (nextRandom == randomInts[j])
{
i--; //try again
j = i;
}
}
}
//Board value must be odd number for getting our desired goal state
for(int u=0;u<9;u++)
{
for(int w=s;w<8;w++)
{
if(randomInts[u]>randomInts[w+1])
if(randomInts[w+1]!=0)
value++;//counting board value
}
count+=value;
s++;
value=0;
}
return count;
}
}  
public class Puzzle1 extends Applet implements  KeyListener,Runnable
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String s="WELCOME TO THE PUZGAME";
String b="press the arrow key buttons to move!!";
int flag=0;
int moves=0;
int a[][] =new int[3][3];
node n=new node();
Thread t=new Thread();
int goal[]={1,2,3,8,0,4,7,6,5};
public void init()
{	

	
try
{
	setBackground(Color.red);
	setForeground(Color.BLUE);
t.start();
int val=n.randomize();
//if board value is even,rearrange to make it odd
if(val%2==0)
{
if(n.randomInts[0]!=0&&n.randomInts[1]!=0)
{
int temp=n.randomInts[0];
n.randomInts[0]=n.randomInts[1];
n.randomInts[1]=temp;
}
else
{
if(n.randomInts[0]!=0)
{
int temp=n.randomInts[0];
n.randomInts[0]=n.randomInts[2];
n.randomInts[2]=temp;
}
else
{
int temp=n.randomInts[1];
n.randomInts[1]=n.randomInts[2];
n.randomInts[2]=temp;
}
}
}
}
catch(Exception e)
{}     
//casting one dimensional array to two dimensional array for
//convenience
int z=0;
for(int x=0;x<3;x++)
{
for(int y=0;y<3;y++)
{
a[x][y]=n.randomInts[z];
z++;
}
}
addKeyListener(this);
requestFocus();
}
public void keyPressed(KeyEvent k)
{
//making appropriate rearrangement on key press
int key=k.getKeyCode();
switch(key)
{
case KeyEvent.VK_UP:
for(int i=0;i<3;i++)
{
for(int j=0;j<3;j++)
{
if(a[i][j]==0)
{
if((i!=3))
{
a[i][j]=a[i+1][j];
a[i+1][j]=0;
flag=1;
moves++;
break;
}
else
try
{
showStatus("Move not possible");
Thread.sleep(300);
showStatus(" ");
}
catch(Exception e){}
}             
}
if (flag==1)
break;
}
break;
case KeyEvent.VK_DOWN:
for(int i=0;i<3;i++)
{
for(int j=0;j<3;j++)
{
if(a[i][j]==0)
{
if(i!=3)
{
a[i][j]=a[i-1][j];
a[i-1][j]=0;
flag=1;
moves++;
break;
}
else
try
{
showStatus("Move not possible");
Thread.sleep(300);
showStatus(" ");
}
catch(Exception e){}
}
}
if (flag==1)
break;
}
break;
case KeyEvent.VK_RIGHT:
for(int i=0;i<3;i++)
{
for(int j=0;j<3;j++)
{
if(a[i][j]==0)
{
if(j!=3)
{
a[i][j]=a[i][j-1];
a[i][j-1]=0;
flag=1;
moves++;
break;
}
else
try
{
showStatus("Move not possible");
Thread.sleep(300);
showStatus(" ");
}
catch(Exception e){}
}
}
if (flag==1)
break;
}
break;
case KeyEvent.VK_LEFT:
for(int i=0;i<3;i++)
{
for(int j=0;j<3;j++)
{
if(a[i][j]==0)
{
if(j!=3)
{
a[i][j]=a[i][j+1];
a[i][j+1]=0;
flag=1;
moves++;
break;
}
else
try
{
showStatus("Move not possible");
Thread.sleep(300);
showStatus(" ");
}
catch(Exception e){}
}
}
if (flag==1)
break;
}
break;
}
flag=0;
repaint();
}
public void keyReleased(KeyEvent k)
{
}
public void keyTyped(KeyEvent k)
{
repaint();
}
public void paint(Graphics g)
{

Font f=new Font("TimesNewRoman",Font.ITALIC,40);
g.setFont(f);
g.drawString(s,450,40);
g.drawString(b,450,140);

int array[]=new int[9];
//code for creating the grid look
g.setColor(Color.red);
int x=40;
int h=60,w=60;
Image test=createImage(250,250);
Graphics gc=null;
gc=g;
g=test.getGraphics();
//creating 9 button shaped boxes
g.fillRect(40,40,120,120);
g.setColor(Color.green);
g.fillRoundRect(45,45,30,30,60,60);
g.fillRoundRect(85,45,30,30,60,60);
g.fillRoundRect(125,45,30,30,60,60);
g.fillRoundRect(45,85,30,30,60,60);
g.fillRoundRect(85,85,30,30,60,60);
g.fillRoundRect(125,85,30,30,60,60);
g.fillRoundRect(45,125,30,30,60,60);
g.fillRoundRect(85,125,30,30,60,60);
g.fillRoundRect(125,125,30,30,60,60);
g.setColor(Color.black);
//printing the numbers in appropriate boxes
for(int i=0;i<3;i++)
{
for(int j=0;j<3;j++)
{
if(a[i][j]==0)
g.drawString(" ",420,30);
else
g.drawString(String.valueOf(a[i][j]),h,w);
h=h+x;
}
h=60;
w=w+x;
}
int z=0;
for(int r=0;r<3;r++)
{
for(int s=0;s<3;s++)
{
array[z]=a[r][s];
z++;
}
}
//checking current and goal node for equality
boolean val=Arrays.equals(goal,array);
if(val)
{
g.drawString("MOVES="+moves,40,220);
g.drawString("YOU HAVE WON THE GAME!",40,180);
g.drawString("CONGRATULATIONS!",40,200);
}
gc.drawImage(test, 0,0,null);
}
public void update(Graphics g)
{
paint(g);
}
public void run()
{
}
public static void main(String args[]){
    
}
}