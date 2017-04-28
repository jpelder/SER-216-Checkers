package checkers.gui;

import checkers.logic.GameEngine;
import checkers.logic.CheckerMove;

import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Color;

/**
 * The core screen/GUI component -> utilizes game logic
 * SER 216 - Group 15 (Edited Initial Code)
 * Date: 4-18-17
 */
public class Checkers extends JPanel implements ActionListener, ItemListener, MouseMotionListener, MouseListener {

    public Graphics g;

    JTextArea msg = new JTextArea("                            Select Mode and Color then Start New Game!!");
    public ImageIcon redN = new ImageIcon(new ImageIcon(getClass().getResource("../resources/images/red_normal.jpg")).getImage());//red_normal.jpg
    public ImageIcon yellowN = new ImageIcon(new ImageIcon(getClass().getResource("../resources/images/yellow_normal.jpg")).getImage());//yellow_normal.jpg
    public ImageIcon redK = new ImageIcon(new ImageIcon(getClass().getResource("../resources/images/red_king.jpg")).getImage());//red_king.jpg
    public ImageIcon yellowK = new ImageIcon(new ImageIcon(getClass().getResource("../resources/images/yellow_king.jpg")).getImage());//yellow_king.jpg
    ImageIcon hlp = new ImageIcon(new ImageIcon(getClass().getResource("../resources/images/help.jpg")).getImage());//help.jpg
    ImageIcon snp = new ImageIcon(new ImageIcon(getClass().getResource("../resources/images/sound.jpg")).getImage());//sound.jpg
    ImageIcon mup = new ImageIcon(new ImageIcon(getClass().getResource("../resources/images/mute.jpg")).getImage());//mute.jpg
    JButton nwB = new JButton("New Game");
    JButton unB = new JButton("Undo");
    JButton hlpB=new JButton(hlp);
    JButton snB=new JButton(snp);
    JButton allKing = new JButton("Kings!");

    ButtonGroup players = new ButtonGroup();
    JRadioButton p1 = new JRadioButton("1-Player", true);
    JRadioButton p2 = new JRadioButton("2-Player", false);
    
    ButtonGroup randomize = new ButtonGroup();
    JRadioButton defaultOpt = new JRadioButton("Default", true);
    JRadioButton randomed = new JRadioButton("Random",false);
    
    ButtonGroup colors = new ButtonGroup();
    JRadioButton c1 = new JRadioButton("Red", false);
    JRadioButton c2 = new JRadioButton("Yellow", true);

    Help hp = new Help();

    JLabel mode = new JLabel("Mode");
    JLabel col = new JLabel("Color");
    JLabel diff = new JLabel("Difficulty Level");
    JLabel rndm = new JLabel("Random");
    JLabel rp = new JLabel();
    JLabel rpt = new JLabel("Regular Pieces");
    JLabel bp = new JLabel();
    JLabel rk = new JLabel();
    JLabel rkt = new JLabel("Kings");
    JLabel bk = new JLabel();

    JComboBox level = new JComboBox();

    String selectedColor;
    int selectedMode;
    int difficulty;
    int previousTileYellow, previousTileRed, tempPrevious, tempPreviousCP, moveYellow, moveRed;
    boolean isRandom;

    public static final int redNormal = 1;
	public static final int yellowNormal = 2;
	public static final int redKing = 3;
	public static final int yellowKing = 4;
	public static final int empty = 0;


    public int currType;
    public boolean movable;

    public int[][] board = new int[8][8];

    public int [][] preBoard1 = new int[8][8];  //for undo
    public int preToMove1;
    public int [][] preBoard2 = new int[8][8];
    public int preToMove2;
    public int [][] preBoard3 = new int[8][8];
    public int preToMove3;

    public int startX, startY, endX, endY;
    public boolean incomplete = false;
    public boolean highlight=false;

    public int toMove = redNormal;
	public int loser = empty;

    public static boolean silent = false;

    public int undoCount;

    public int won = 0;

    Point winPoint;

    public Checkers(){
        setupGUI();
    }

    private void setupGUI(){
        setLayout(null);

        nwB.setFocusPainted(false);
        unB.setFocusPainted(false);
        c1.setFocusPainted(false);
        c2.setFocusPainted(false);
        p1.setFocusPainted(false);
        p2.setFocusPainted(false);
        defaultOpt.setFocusPainted(false);
        randomed.setFocusPainted(false);
        hlpB.setFocusPainted(false);
        snB.setFocusPainted(false);

        diff.setFont(new Font("SansSerif", Font.PLAIN, 11));
        col.setFont(new Font("SansSerif", Font.BOLD, 11));
        mode.setFont(new Font("SansSerif", Font.PLAIN, 11));
        c1.setFont(new Font("SansSerif", Font.PLAIN, 11));
        c2.setFont(new Font("SansSerif", Font.PLAIN, 11));
        p1.setFont(new Font("SansSerif", Font.PLAIN, 11));
        p2.setFont(new Font("SansSerif", Font.PLAIN, 11));
        rndm.setFont(new Font("SansSerif", Font.PLAIN, 11));
        defaultOpt.setFont(new Font("SansSerif", Font.PLAIN,11));
        randomed.setFont(new Font("SansSerif", Font.PLAIN, 11));
        nwB.setFont(new Font("SansSerif", Font.BOLD, 11));
        unB.setFont(new Font("SansSerif", Font.BOLD, 11));
        hlpB.setFont(new Font("SansSerif", Font.PLAIN, 11));
        snB.setFont(new Font("SansSerif", Font.PLAIN, 11));
        allKing.setFont(new Font("SansSerif", Font.BOLD, 11));
        msg.setFont(new Font("SansSerif", Font.BOLD, 12)); 
        
        snB.setBackground(Color.blue);
        hlpB.setBackground(Color.blue);
        nwB.setBackground(Color.green);
        unB.setBackground(Color.yellow);
        unB.setForeground(Color.red);
        allKing.setBackground(Color.magenta);
        allKing.setEnabled(false);
        
        nwB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        unB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        hlpB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        snB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        nwB.addActionListener(this);
        unB.addActionListener(this);
        hlpB.addActionListener(this);
        snB.addActionListener(this);
        allKing.addActionListener(this);
        nwB.setBounds(405, 55, 95, 25);//297
        this.add(nwB);
        unB.setBounds(405, 85, 95, 25);
        this.add(unB);
        allKing.setBounds(405, 115, 95, 25);
        this.add(allKing);
        hlpB.setBounds(415, 10, 25, 25);
        this.add(hlpB);
        snB.setBounds(460, 10, 25, 25);
        this.add(snB);

        mode.setBounds(420, 280, 80, 25);
        this.add(mode);
        p1.addActionListener(this);
        p2.addActionListener(this);
        p1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        p2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        players.add(p1);
        players.add(p2);
        p1.setBounds(415, 300, 80, 25);
        p2.setBounds(415, 328, 80, 25);
        this.add(p1);
        this.add(p2);
        
        rndm.setBounds(420, 360, 80, 25);
        this.add(rndm);
        defaultOpt.addActionListener(this);
        randomed.addActionListener(this);
        defaultOpt.setCursor(new Cursor(Cursor.HAND_CURSOR));
        randomed.setCursor(new Cursor(Cursor.HAND_CURSOR));
        randomize.add(defaultOpt);
        randomize.add(randomed);
        defaultOpt.setBounds(415,380,80,25);
        randomed.setBounds(415,400,80,25);
        this.add(defaultOpt);
        this.add(randomed);
        
        col.setBounds(420, 200, 80, 25);
        col.setForeground(Color.green);
        this.add(col);
        c1.addActionListener(this);
        c2.addActionListener(this);
        c1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        c2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        c1.setBackground(Color.red);
        c2.setBackground(Color.yellow);
        colors.add(c1);
        colors.add(c2);
        c1.setBounds(415, 248, 80, 25);
        c2.setBounds(415, 220, 80, 25);
        this.add(c1);
        this.add(c2);

        level.setCursor(new Cursor(Cursor.HAND_CURSOR));
        level.addItemListener(this);
        level.addItem("Easy");
        level.addItem("Moderate");
        level.addItem("Difficult");
        level.setSelectedIndex(0);
        level.setBounds(415, 170, 80, 25);
        this.add(level);

        diff.setCursor(new Cursor(Cursor.HAND_CURSOR));
        diff.setBounds(415, 140, 100, 25);
        this.add(diff);

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        msg.setBounds(0, 405, 400, 20);
        msg.setEnabled(false);
        this.add(msg);

        //The pieces at the bottom
        rp.setBounds(90, 433, 50, 50);
        rp.setIcon(yellowN);
        this.add(rp);
        bp.setBounds(140, 433, 50, 50);
        bp.setIcon(redN);
        this.add(bp);
        rk.setBounds(320, 433, 50, 50);
        rk.setIcon(yellowK);
        this.add(rk);
        bk.setBounds(370, 433, 50, 50);
        bk.setIcon(redK);
        this.add(bk);

        //Labels describing pieces
        rpt.setBounds(10, 450, 100, 15);
        this.add(rpt);
        rkt.setBounds(280, 450, 60, 20);
        this.add(rkt);
        
        //g=getGraphics();
        //g.drawImage(redN.getImage(),30,450,this);
        
        //SET TOOL TIPS FOR BUTTONS
        hlpB.setToolTipText("Rules");
        snB.setToolTipText("Mute");

    }
    

    public void paintComponent(Graphics g)	{
		super.paintComponent(g);
        g.setColor(new Color(0, 0, 0));

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                g.fillRect(100 * j, 100 * i, 50, 50);
            }
        }
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                g.fillRect(50 + 100 * j, 50 + 100 * i, 50, 50);
            }
        }
        g.drawLine(0, 400, 400, 400);
        g.drawLine(400, 0, 400, 400);
        drawCheckers();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("1-Player")){
            new PlaySound("../resources/sounds/option.wav").start();
            col.setEnabled(true);
            col.setVisible(true);
            diff.setEnabled(true);
            diff.setVisible(true);
            c1.setEnabled(true);
            c1.setVisible(true);
            c2.setEnabled(true);
            c2.setVisible(true);
            level.setEnabled(true);
            level.setVisible(true);
            rndm.setEnabled(true);
            defaultOpt.setEnabled(true);
            randomed.setEnabled(true);
            rndm.setVisible(true);
            defaultOpt.setVisible(true);
            randomed.setVisible(true);
        }
        if(e.getActionCommand().equalsIgnoreCase("2-Player")){
            new PlaySound("../resources/sounds/option.wav").start();
            col.setEnabled(false);
            col.setVisible(false);
            diff.setEnabled(false);
            diff.setVisible(false);
            c1.setEnabled(false);
            c1.setVisible(false);
            c2.setEnabled(false);
            c2.setVisible(false);
            level.setEnabled(false);
            level.setVisible(false);
            c2.setSelected(true);
            rndm.setEnabled(false);
            defaultOpt.setEnabled(false);
            randomed.setEnabled(false);
            rndm.setVisible(false);
            defaultOpt.setVisible(false);
            randomed.setVisible(false);
            defaultOpt.setSelected(true);
        }
        if(e.getActionCommand().equalsIgnoreCase("red")){
            new PlaySound("../resources/sounds/option.wav").start();
        }
        if(e.getActionCommand().equalsIgnoreCase("yellow")){
            new PlaySound("../resources/sounds/option.wav").start();
        }
        if(e.getActionCommand().equalsIgnoreCase("New Game")){
            new PlaySound("../resources/sounds/button.wav").start();
            newGame();
        }
        if(e.getActionCommand().equalsIgnoreCase("Default")){
        	new PlaySound("../resources/sounds/option.wav");
        }
        if(e.getActionCommand().equalsIgnoreCase("Random")){
        	new PlaySound("../resources/sounds/option.wav");
        }
        if(e.getActionCommand().equalsIgnoreCase("Undo") && undoCount>3){
            new PlaySound("../resources/sounds/button.wav").start();
            undo();
        }
        if(e.getActionCommand().equalsIgnoreCase("kings!")){
        	kingAll();
        }
        if(e.getSource()==hlpB){
            new PlaySound("../resources/sounds/button.wav").start();
            hp.setVisible(true);
        }
        if(e.getSource()==snB){
            if(silent){
                snB.setIcon(snp);
                silent=false;
                new PlaySound("../resources/sounds/button.wav").start();
            }
            else{
                snB.setIcon(mup);
                silent=true;
            }
        }
    }

    /**
     * Go through the board and king all remaining pieces 
     */
    public void kingAll(){
    	System.out.println("KING BUTTON PRESSED");
    	for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
            	if(board[i][j] == redNormal){
            		board[i][j] = redKing;
            	}
            	else if(board[i][j] == yellowNormal){
            		board[i][j] = yellowKing;
            	}
            }
    	}
    	drawCheckers();
    }
    
    public void newGame(){    //creates a new game
    	moveYellow = 0;
    	moveRed = 0;
    	allKing.setEnabled(true);
    	
        //Yellow takes the first move in both modes
        //If someone wants to move secondly, red has to be selected
        //Yellow is always at the bottom of the board

        selectedColor = c1.isSelected() ? "red" : "yellow";
        selectedMode = p1.isSelected()?1:2;
        difficulty = level.getSelectedIndex();
        isRandom = defaultOpt.isSelected();

        unB.setEnabled(false);

        won = 0;

        undoCount = 0;


        highlight = false;
		incomplete = false;

        loser = empty;

        for(int i = 0; i < 8; i++)/*applies values to the board*/ {
			for(int j = 0; j < 8; j++){
				board[i][j] = empty;
			}

			for(int j = 0; j < 3; j++){
			    if( isPossibleSquare(i ,j) ){
				    board[i][j] = redNormal;
			    }
			}

			for(int j = 5; j < 8; j++){
			    if(isPossibleSquare(i,j)){
				    board[i][j] = yellowNormal;
			    }
			}
		}
        
        for(int i = 0; i < 8; i++){
            System.arraycopy(board[i], 0, preBoard1[i], 0, 8); //for undo
            System.arraycopy(preBoard1[i], 0, preBoard2[i], 0, 8);
            System.arraycopy(preBoard2[i], 0, preBoard3[i], 0, 8);
            preToMove3 = preToMove2 = preToMove1 = toMove;
        }
        
        //Generates a random integer value, 1 or 2 to determine who goes first
        //Due to the nature of how red pieces move, player will be able to determine
        //if red piece has moved by highlighted previous square
        
        if(selectedMode == 1){
            if(isRandom == true){
                if (selectedMode == 1 && selectedColor.equalsIgnoreCase("yellow")){
                    this.toMove = redNormal;
                    play();
        		}
        		else if (selectedMode == 1 && selectedColor.equalsIgnoreCase("red")){
                   this.toMove = redNormal;
                    play();
        		}
                
            }else{
                int random = (Math.random() <= 0.5) ? 1:2;
                
                if (selectedMode == 1 && selectedColor.equalsIgnoreCase("yellow") && random == 2){
                	new PlayerPopUp("Red",this.getLocationOnScreen());
                    this.toMove = yellowNormal;
                    play();
        		}else if (selectedMode == 1 && selectedColor.equalsIgnoreCase("yellow") && random == 1){
                	new PlayerPopUp("Yellow",this.getLocationOnScreen());
                    this.toMove = redNormal;
                    play();
        		}
        		else if (selectedMode == 1 && selectedColor.equalsIgnoreCase("red") && random == 1){
                	new PlayerPopUp("Yellow",this.getLocationOnScreen());
                   this.toMove = redNormal;
                    play();
        		}
        		else if (selectedMode == 1 && selectedColor.equalsIgnoreCase("red") && random == 2){
                	new PlayerPopUp("Red",this.getLocationOnScreen());
                   this.toMove = yellowNormal;
                    play();
        		}
            }
        }else{
        	toMove = yellowNormal;
            if (selectedMode == 2 && selectedColor.equalsIgnoreCase("yellow")){
                this.toMove = redNormal;
                play();
    		}
    		else if (selectedMode == 2 && selectedColor.equalsIgnoreCase("red")){
               this.toMove = redNormal;
                play();
    		}
        }


        update();
        drawCheckers();
        showStatus();
    }

    public void drawCheckers(){ //paint checkers on the board
       g = getGraphics();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
            	if(board[i][j] == redNormal){
                    g.drawImage(redN.getImage(), i * 50, j * 50, this);
                }
                else if(board[i][j] == yellowNormal){
                    g.drawImage(yellowN.getImage(), i * 50, j * 50, this);
                }
                else if(board[i][j] == redKing){
                    g.drawImage(redK.getImage(), i * 50, j * 50, this);
                }
                else if(board[i][j] == yellowKing){
                    g.drawImage(yellowK.getImage(), i * 50, j * 50, this);
                }
            }
        }
       
    }

    public void undo(){//undo function
    	moveYellow = 0;
    	moveRed = 0;
        undoCount = 1;
        for(int i = 0; i < 8; i++){
            System.arraycopy(preBoard3[i], 0, board[i], 0, 8);//copies previous board
        }
        toMove = preToMove3;
        drawCheckers();
        update();
        drawCheckers();

        if(selectedMode == 1){
            play();
        }
    }

    public void play()	{

        undoCount++;

        if(undoCount > 3){
            if(selectedMode == 1 && difficulty != 4){
                unB.setEnabled(true);
            }
            else if(selectedMode == 2){
                unB.setEnabled(true);
            }
        }
        
        for(int i = 0; i < 8; i++){
            System.arraycopy(preBoard2[i], 0, preBoard3[i], 0, 8);
            System.arraycopy(preBoard1[i], 0, preBoard2[i], 0, 8);
            System.arraycopy(board[i], 0, preBoard1[i], 0, 8);
        }
        preToMove3 = preToMove2;
        preToMove2 = preToMove1;
        preToMove1 = toMove;                                                                                  
        int tempScore;
		int[] result = new int[4];
		int[] counter = new int[1];

		counter[0] = 0;
        
		if(this.toMove == yellowNormal && selectedMode==1 && selectedColor.equalsIgnoreCase("yellow")){
			this.toMove = redNormal;
			showStatus();
			if(difficulty == 2){
				tempScore = GameEngine.MinMax(board, 0, difficulty*3 , result, this.toMove, counter);
			}
			else
			{
				tempScore = GameEngine.MinMax(board, 0, difficulty+1, result, this.toMove, counter);
			}
			if(result[0] == 0 && result[1] == 0){
				loser = redNormal;
			}
			else{
                CheckerMove.moveComputer(board, result);
                tempPreviousCP = CheckerMove.previousTile;
        		moveRed = 1;
                if(loser == empty){
                    new PlaySound("../resources/sounds/comPlay.wav").start();
                    play();
                }
                this.toMove = yellowNormal;
			}
		}

		else if(this.toMove == redNormal && selectedMode==1 && selectedColor.equalsIgnoreCase("red")){
			this.toMove = yellowNormal;
			showStatus();
			if(difficulty == 2){
				tempScore = GameEngine.MinMax(board, 0, difficulty*3 , result, this.toMove, counter);
			}
			else
			{
				tempScore = GameEngine.MinMax(board, 0, difficulty+1, result, this.toMove, counter);
			}
			if(result[0] == 0 && result[1] == 0){
				loser = yellowNormal;
			}
			else{
                CheckerMove.moveComputer(board, result);
                tempPreviousCP = CheckerMove.previousTile;
                moveYellow = 1;
                if(loser == empty){
                    new PlaySound("../resources/sounds/comPlay.wav").start();
                    play();
                }

				this.toMove = redNormal;
			}
		}
		else{
            if(this.toMove == redNormal){
				this.toMove = yellowNormal;
            }
			else{
				this.toMove = redNormal;
			}
        }
		if(CheckerMove.noMovesLeft(board, this.toMove)) {
			if(this.toMove == redNormal){
				loser = redNormal;
			}
			else{
				loser = yellowNormal;
			}
		}
        showStatus();
	}

    private boolean isPossibleSquare(int i, int j) {
		if(i<0){
			i = 0;
		}
		if(j<0){
			j = 0;
		}
    	return (i + j) % 2 == 1;
    }

    public void itemStateChanged(ItemEvent e) {          
    }

    public void mousePressed(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();
        int [] square = new int[2];

        if(x >= 0 && x <= 500 && y <= 500 && y >= 0){
            square= CheckerMove.getIndex(x,y);
        }
        
        if(toMove == Checkers.redNormal &&	(board[square[0]][square[1]] == Checkers.redNormal || board[square[0]][square[1]] == Checkers.redKing)|| toMove == Checkers.yellowNormal && (board[square[0]][square[1]] == Checkers.yellowNormal || board[square[0]][square[1]] == Checkers.yellowKing)){

			// we don't want to lose the incomplete move info:
			// only set new start variables if !incomplete
        	//Store the location of this tile to use later
			if(!incomplete){
				highlight = true;
				startX = square[0];
				startY = square[1];
				tempPrevious = (square[0] * 10 + square[1]);		
                update();
                g = getGraphics();
                g.setColor(new Color(255, 100, 30)); //ORANGE
                g.fillRect(50 * square[0], 50 * square[1], 50, 50);                 
                drawCheckers();
                new PlaySound("../resources/sounds/clickChecker.wav").start();
            }
		}
		else if(highlight  && (float)(square[0]+square[1]) / 2 != (square[0]+square[1]) / 2){
			endX = square[0];
			endY = square[1];
			int status = CheckerMove.ApplyMove(board, startX, startY, endX, endY);
			switch (status){
			case CheckerMove.legalMove:
				incomplete = false;
				highlight = false;
				play();
				if((selectedMode == 1) && (selectedColor.equalsIgnoreCase("yellow"))){
					moveYellow = 1;
					previousTileYellow = tempPrevious;
				}
				else if((selectedMode == 1) && (selectedColor.equalsIgnoreCase("red"))){
					moveRed = 1;
					previousTileRed = tempPrevious;
				}
				else{
					if(toMove == redNormal){
						moveYellow = 1;
						previousTileYellow = tempPrevious;
					}	
					else if(toMove == yellowNormal){
						moveRed = 1;
						previousTileRed = tempPrevious;
					}
				}
                update();
                drawCheckers();
                break;
			case CheckerMove.incompleteMove:
				incomplete = true;
				highlight = true;
				// the ending square is now starting square for the next capture
				startX = square[0];
				startY = square[1];
				if((selectedMode == 1) && (selectedColor.equalsIgnoreCase("yellow"))){
					moveYellow = 1;
					previousTileYellow = tempPrevious;
				}
				else if((selectedMode == 1) && (selectedColor.equalsIgnoreCase("red"))){
					moveRed = 1;
					previousTileRed = tempPrevious;
				}
				else{
					if(toMove == redNormal){
						moveYellow = 1;
						previousTileYellow = tempPrevious;
					}	
					else if(toMove == yellowNormal){
						moveRed = 1;
						previousTileRed = tempPrevious;
					}
				}
                update();
                g = getGraphics();
                g.setColor(new Color(255, 100, 30));
                g.fillRect(50 * square[0], 50* square[1], 50, 50);
                drawCheckers();
                break;
			}
        }
	}

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
	
    private void showStatus() {//prints msgs to the status bar
        if(this.toMove == redNormal){
        	if(selectedMode == 1){
        		msg.setText("                                             Player move (Red)");
        	}
        	else{
        		msg.setText("                                                Red move");
        	}
            if(selectedMode != 1){
            	//new HaveToJump("Red", this.getLocationOnScreen());
            }
        }
        else{
        	if(selectedMode == 1){
        		msg.setText("                                             Player move (Yellow)");
        	}
        	else{
        		msg.setText("                                                Yellow move");
        	}
        }

        if(loser == redNormal && won == 0){
            msg.setText("                                               Yellow Wins!");
            try{
                Thread.sleep(150);
            } 
            catch(InterruptedException e) {
                e.printStackTrace();
            }
            new GameWin("Yellow", this.getLocationOnScreen());
            won = 1;
            undoCount = 0;
            newGame();
        }
        else if(loser == yellowNormal && won == 0){
            msg.setText("                                               Red Wins!");
            try{
                Thread.sleep(150);
            } 
            catch(InterruptedException e) {
                e.printStackTrace();
            }  
            new GameWin("Red", this.getLocationOnScreen());
            won = 1;
            undoCount = 0;
            newGame();            
        }
    }
    
    /**
     * Use the variable 'previousTile' to maintain the location of the last used tile, and highlight it
     * This highlights the yellow tile
     */
    public void highlightPreviousYellow(){
	    g = getGraphics();
	    g.setColor(new Color(0, 200, 30)); //LIGHT GREEN
	    if((selectedMode == 1) && (selectedColor.equalsIgnoreCase("red"))){
	    	previousTileYellow = tempPreviousCP;
	    }
	    g.fillRect(50 * (previousTileYellow / 10), 50 * (previousTileYellow % 10), 50, 50);
    }
    
    /**
     * Use the variable 'previousTile' to maintain the location of the last used tile, and highlight it
     * This highlights the yellow tile
     * This still shows up at the end at the moment.
     */
    public void highlightPreviousRed(){
	    g = getGraphics();
	    g.setColor(new Color(0, 191, 255)); //LIGHT BLUE
	    if((selectedMode == 1) && (selectedColor.equalsIgnoreCase("yellow"))){
	    	previousTileRed = tempPreviousCP;
	    }
	    g.fillRect(50 * (previousTileRed / 10), 50 * (previousTileRed % 10), 50, 50);
    }
    
   // The AWT invokes the update() method in response to the repaint() method
   // calls that are made as a checker is dragged. The default implementation
   // of this method, which is inherited from the Container class, clears the
   // applet's drawing area to the background color prior to calling paint().
   // This clearing followed by drawing causes flicker. CheckerDrag overrides
   // update() to prevent the background from being cleared, which eliminates
   // the flicker.
    //@Override
    public void update(){                                                                                                     
    	paint(g);
        if(moveYellow == 1){
        	highlightPreviousYellow();
        }
        if(moveRed == 1){
        	highlightPreviousRed();
        }
    }    
    
}