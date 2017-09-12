/*****************************************************************************\
*  RedBlack.java                   by: TheSilverDirk / Michael Conrad         *
*                                                  and                        *
*                                           Prof. John Franco                 *
*                                                 ECECS                       *
*                                        University of Cincinnati             *
*                                        franco@gauss.ececs.uc.edu            *
*                                                                             *
*  Created: 12/03/2002             Last Modified: 12/10/2002                  *
*                                                                             *
*  Copyright (c) 2002 by Michael Conrad and John Franco                       *
*       This code may be freely distributed under the terms of the            *
*       GNU General Public License.                                           *
*                                                                             *
*  Based on the description of Red-Black Tree algorithms found in             *
*       Berman and Paul.                                                      *
*       Sequential and Parallel Algorithms.                                   *
*       Brooks/Cole PWS Publishing Co,                                        *
*       1997 (ISBN:0-534-94674-7).                                            *
*                                                                             *
*  This is a special version which reads numbers from a parameter list and    *
*  installs objects with those numbers into the red black tree upon startup.  *
*  A sample applet tag looks like this:                                       *
*                                                                             *
*  <applet code="RedBlack.class" width=1024 height=450>                       *
*  <param name="args" value="12 6 25 10 3 18 55 11 7 4 2 15 21 33">           *
*  </applet>                                                                  *
*                                                                             *
\*****************************************************************************/
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;

/*****************************************************************************\
 *  This section contains interfaces which customize the type of object      *
 *  being stored in the Red-Black tree and implementations of those          *
 *  for simple Integer objects.  All storable objects must implement         *
 *  interface TreeObject which requires identity and value (for order).      *
 *  For comparing objects, interfaces RBTree_inorder_class and               *
 *  RBTree_compare_class must be implemented (they prototype two comparison  *
 *  methods).  Finally, TokenObjects are merely used to facilitate click-    *
 *  driven operation of the applet and are not part of the Red-Black tree    *
 *  concept.                                                                 *
\*****************************************************************************/

// All storeable data items must implement the TreeObject interface
interface TreeObject {
   int getValue ();
   String getIdent ();
}

// In this example, we are storing simple integer objects
class IntObject implements TreeObject {
   int x;
   IntObject (int x) { this.x = x; }
   public int getValue () { return x; }
   public String getIdent () { return String.valueOf(x); }
}

// This interface prototypes the method for comparing one object
// against another to determine which is of lower order
interface RBTree_inorder_class {
   boolean compare (TreeObject obj1, TreeObject obj2);
}

// In this example of simple integer objects the above interface is
// implemented as follows
class IntInorderObject implements RBTree_inorder_class {
   public boolean compare (TreeObject obj1, TreeObject obj2) {
      return obj1.getValue() <= obj2.getValue();
   }
}

// We request a second version of comparison which compares the order number
// of single objects against a previously stored value
interface RBTree_compare_class {
   int evaluate (TreeObject object);
}

// An implementation of the above for the simple integers.
class IntCompare implements RBTree_compare_class {
   int x;
   IntCompare (int x) { this.x = x; }

   public int evaluate (TreeObject object) {
      if (x < object.getValue()) return -1;
      if (x > object.getValue()) return 1;
      return 0;
   }
}

class TO implements TokenObject { 
   public boolean value; 
   TO (boolean b) { value = b; }
}

/*****************************************************************************\
 *  This section of code contains the red-black tree library functions and   *
 *  classes.  They are written to allow any type of data objects with some   *
 *  well defined notion of order, expressed as a positive integer value, to  *
 *  be stored in a red-black tree.                                           *
\*****************************************************************************/

// Dot objects provide the infrastruture for maintaining interesting data
// objects (implementing TreeObject interface) and rendering information.
class Dot {
   int   level, indent;
   float left, top;
   Dot   leftTree, rightTree, parent, sentinel;
   Color color, disp_color;
   TreeObject object;

   public Dot () { }

   public Dot (Dot sentinel) {  
      disp_color = color = Color.blue;  
      this.sentinel = sentinel; 
      level = 0;
      indent = 0;
   }

   public Dot (int n, Color clr) {
      color = disp_color = clr;
      object = new IntObject(n);
      leftTree = null;
      rightTree = null;
   }

   public Dot (RBTree tree, int n, Color clr) {
      sentinel = tree.sentinel;
      leftTree = sentinel;
      rightTree = sentinel;
      color = disp_color = clr;
      object = new IntObject(n);
      level = 0;
      indent = 0;
      parent = tree.rootSentinel;
      left = 0;
      top = 0;
   }

   // Finds the next lowest ordered stored object.  Returns null if
   // none exists.
   // Used when deleting a node to find which node should take its place
   // in the tree.
   public Dot getPrev() {
      Dot current = this;
      // If we are not at a leaf, move to the right-most node
      // in the tree to the left of this node.
      if (current.leftTree.leftTree != current.leftTree) {
         current = current.leftTree;
         while (current.rightTree.rightTree != current.rightTree)
            current = current.rightTree;
         return current;
      }
      // Else walk up the tree until we see a parent node to the left
      else {
         Dot cur_parent = current.parent;
         while (cur_parent.leftTree == current) {
            current = cur_parent;
            cur_parent= cur_parent.parent;
            // Check for rootSentinel
            if (cur_parent == null) return null;
         }
         return cur_parent;
      }
   }

   // Finds the next highest stored object.  Returns null if none exists.
   // Used when deleting a node to find which node should take its place
   // in the tree.
   public Dot getNext() {
      Dot current = this;
      // If we are not at a leaf, move to the left-most node
      //  in the tree to the right of this node.
      if (current.rightTree.rightTree != current.rightTree) {
         current = current.rightTree;
         while (current.leftTree.leftTree != current.leftTree)
            current = current.leftTree;
         return current;
      }
      // Else walk up the tree until we see a parent node to the right
      else {
         Dot cur_parent = current.parent;
         while (cur_parent.rightTree == current) {
            current = cur_parent;
            cur_parent = current.parent;
         }
         // Check for the rootSentinel
         if (cur_parent.parent == null) return null;
         return cur_parent;
      }
   }

   public void rightRotate( ) {
      if (this.parent.leftTree == this)
         leftSide_RightRotate( );
      else
         rightSide_RightRotate( );
   }

   public void leftRotate( ) {
      if (this.parent.leftTree == this)
         leftSide_LeftRotate( );
      else
         rightSide_LeftRotate( );
   }

   public void leftSide_LeftRotate() {
      Dot temp = this.parent; // temp is used for parent
      Dot child = this.rightTree;

      temp.leftTree = child;
      child.parent = temp;

      temp = child.leftTree; // temp is now used for grandchild
      this.rightTree = temp;
      temp.parent = this;
      
      child.leftTree = this;
      this.parent = child;
   }

   public void leftSide_RightRotate() {
      Dot temp = this.parent; // temp is used for parent
      Dot child = this.leftTree;

      temp.leftTree = child;
      child.parent = temp;

      temp = child.rightTree; // temp is now used for grandchild
      this.leftTree = temp;
      temp.parent = this;
      
      child.rightTree = this;
      this.parent = child;
   }

   public void rightSide_RightRotate( ) {
      Dot temp = this.parent; // temp is used for parent
      Dot child = this.leftTree;

      temp.rightTree = child;
      child.parent = temp;

      temp = child.rightTree; // temp is now used for grandchild
      this.leftTree = temp;
      temp.parent = this;

      child.rightTree = this;
      this.parent = child;
   }

   public void rightSide_LeftRotate( ) {
      Dot temp = this.parent; // temp is used for parent
      Dot child = this.rightTree;

      temp.rightTree = child;
      child.parent = temp;

      temp = child.leftTree; // temp is now used for grandchild
      this.rightTree= temp;
      temp.parent = this;

      child.leftTree = this;
      this.parent = child;
   }
}

// The next four classes bear the brunt.  They extend a Stream class in order
// to be able to interrupt their operation to display intermediate changes in 
// the red-black tree.  To use these classes without the GUI remove all 
// occurrences of "rbt.level()", "putIt(tot)", and "putIt(null)".  The 
// additional slight change of not extending the Stream class, moving the 
// "run" functions inside the RBTree and Dot classes as needed (replacing 
// the name "run" with a descriptive method name), and invoking those methods 
// instead of grabbing a new object of one of these classes and invoking 
// "next" will streamline the code even further.  
//
// Used when adding a node to put the tree in balance
class Balance extends Stream {
   // current is the parent node of the node just added.  The child is red.
   Dot current;
   RBTree rbt;
   TO tot = new TO(true);

   public Balance (RBTree rbt, Dot dot) { 
      this.rbt = rbt;
      current = dot; 
   }

   public void run () {
      // if Current is a black node, no rotations needed
      while (current.color != Color.black) {
         // if (!Current->Parent) break;  XXX may not need this
         // Current is red, the imbalanced child is red, and parent is black.

         Dot cur_parent = current.parent;

         // If the current is on the right of the parent, the parent is 
         // to the left
         if (cur_parent.rightTree == current) {
            // if the sibling is also red, we can pull down the color 
            // black from the parent
            if (cur_parent.leftTree.color == Color.red) {
               cur_parent.leftTree.color = 
                  cur_parent.leftTree.disp_color = Color.black;
               current.color = current.disp_color = Color.black;
               cur_parent.color = cur_parent.disp_color = Color.red;
               rbt.level();
               putIt(tot);
               // jump twice up the tree. if Current reaches the rootSentinel 
               // (black node), the loop will stop
               current = cur_parent.parent;
               continue;
            }
            // if the imbalance (red node) is on the left, and the parent 
            // is on the left, a "prep-slide" is needed. (see diagram)
            if (current.leftTree.color == Color.red) {
               current.rightSide_RightRotate( );
               rbt.level();
               putIt(tot);
            }

            // Now we can do our left rotation to balance the tree.
            cur_parent.leftRotate();
            rbt.level();

            if (cur_parent.color == Color.red && 
                cur_parent.parent.color == Color.black) {
               putIt(null);
               return;
            } else {
               putIt(tot);
               cur_parent.color = cur_parent.disp_color = Color.red;
               cur_parent.parent.color = 
                  cur_parent.parent.disp_color = Color.black;
               rbt.level();
               putIt(null);
               return;
            }
         }
         // else the parent is to the right
         else {
            // if the sibling is also red, we can pull down the color black 
            // from the parent
            if (cur_parent.rightTree.color == Color.red) {
               cur_parent.rightTree.color = 
                  cur_parent.rightTree.disp_color = Color.black;
               current.color = current.disp_color = Color.black;
               cur_parent.color = cur_parent.disp_color = Color.red;
               rbt.level();
               putIt(tot);
               // jump twice up the tree. if Current reaches the rootSentinel 
               // (black node), the loop will stop
               current = cur_parent.parent;
               continue;
            }
            // if the imbalance (red node) is on the right, and the parent 
            // is on the right, a "prep-slide" is needed. (see diagram)
            if (current.rightTree.color == Color.red) {
               current.leftSide_LeftRotate( );
               rbt.level();
               putIt(tot);
            }
            
            // Now we can do our left rotation to balance the tree.
            cur_parent.rightRotate( );
            rbt.level();
            if (cur_parent.color == Color.red &&
                cur_parent.parent.color == Color.black) {
               putIt(null);
               return;
            } else {
               putIt(tot);
               cur_parent.color = cur_parent.disp_color = Color.red;
               cur_parent.parent.color = 
                  cur_parent.parent.disp_color = Color.black;
               rbt.level();
               putIt(null);
               return;
            }
         }
      }
      rbt.level();
      putIt(null);
      return;
   }
}

// For adding an object to the red-black tree.
class Add extends Stream {
   Dot newNode;
   RBTree_inorder_class inorder;
   Dot sentinel, rootSentinel;
   RBTree rbt;
   TO tot = new TO(true);
   TO tof = new TO(false);

   public Add (RBTree rbt, Dot newNode, RBTree_inorder_class inorder) {
      this.newNode = newNode;
      this.inorder = inorder;
      sentinel = rbt.sentinel;
      rootSentinel = rbt.rootSentinel;
      this.rbt = rbt;
   }

   public void run () {
      if (newNode.color != Color.blue) {
         putIt(null);
         return;
      }

      newNode.color = newNode.disp_color = Color.red;
      newNode.sentinel = sentinel;
      newNode.leftTree =  sentinel;
      newNode.rightTree = sentinel;

      Dot current = rootSentinel.leftTree;
      if (current == sentinel) {
         rootSentinel.leftTree = newNode;
         newNode.parent = rootSentinel;
      } else {
         do {
            // if the new node comes before the current node, go left
            if (inorder.compare( newNode.object, current.object )) {
               if (current.leftTree == sentinel) {
                  current.leftTree = newNode;
                  newNode.parent = current;
                  rbt.level();
                  putIt(tot);
                  break;
               }
               else current = current.leftTree;
            } else {  // go right
               if (current.rightTree == sentinel) {
                  current.rightTree = newNode;
                  newNode.parent = current;
                  rbt.level();
                  putIt(tot);
                  break;
               }
               else current = current.rightTree;
            }
         } while (true);
         TO bal;
         Balance balance = new Balance(rbt, current);
         while ((bal = (TO)balance.next()) != null) {
            rbt.level();
            putIt(bal);
         }
         balance = null;
      }
      rootSentinel.leftTree.color = 
         rootSentinel.leftTree.disp_color = Color.black;
      rbt.level();
      putIt(null);
      return;
   }
}

// For removing an object from a red-black tree.
class Prune extends Stream {
   RBTree rbt;
   Dot current;
   TO tot = new TO(true);

   public Prune (RBTree rbt, Dot dot) { current = dot; this.rbt = rbt; }

   public void run () {
      if (current.color == Color.blue) {
         putIt(null);
         return;
      }
      // If this is a leaf node (or almost a leaf) we can just prune it
      if (current.leftTree.leftTree == current.leftTree || 
          current.rightTree.rightTree == current.rightTree) {
         TO pru;
         PruneLeaf pruner = new PruneLeaf(rbt, current);
         while ((pru = (TO)pruner.next()) != null) {
            rbt.level();
            putIt(pru);
         }
         pruner = null;
      }
      // Otherwise we need a successor.  We are guaranteed to have one because
      // the current node has 2 children.
      else {
         Dot successor = current.getNext( );
         // Do we like this successor?  If not, get the other one.
         if (successor.color == Color.black && 
             successor.leftTree.leftTree == successor.leftTree && 
             successor.rightTree.rightTree == successor.rightTree)
            successor = current.getPrev( );
         successor.disp_color = Color.yellow; // JVF

         TO pru;
         PruneLeaf pruner = new PruneLeaf(rbt, successor);
         while ((pru = (TO)pruner.next()) != null) {
            rbt.level();
            putIt(pru);
         }
         pruner = null;

         // now exchange the successor for the current node
         Dot Temp = current.rightTree;
         successor.rightTree = Temp;
         Temp.parent = successor;
         
         Temp = current.leftTree;
         successor.leftTree = Temp;
         Temp.parent = successor;

         Temp = current.parent;
         successor.parent = Temp;
         if (Temp.leftTree == current) Temp.leftTree = successor; 
         else Temp.rightTree = successor;
         successor.color = successor.disp_color = current.color;

         rbt.level();
         putIt(tot);
      }
      current.color = current.disp_color = Color.blue;
      rbt.level();
      putIt(null);
      return;
   }
}

// PruneLeaf performs pruning of nodes with at most one child.
// Used by Prune.
class PruneLeaf extends Stream {
   TO tot = new TO(true);
   Dot node;
   RBTree rbt;

   public PruneLeaf (RBTree rbt, Dot dot) { node = dot; this.rbt = rbt; }

   public void run () {
      Dot node_parent = node.parent;
      boolean leftSide = (node_parent.leftTree == node);

      // if the node is red and has at most one child, then it has no child.
      // So prune it.
      if (node.color == Color.red) {
         if (leftSide) node_parent.leftTree = rbt.sentinel;
         else node_parent.rightTree = rbt.sentinel;
         rbt.level();
         putIt(null);
         return;
      }

      // Node is black here.  If it has a child, the child will be red.
      if (node.leftTree != rbt.sentinel) {
         // swap with child
         node.leftTree.color = node.leftTree.disp_color = Color.black;
         node.leftTree.parent = node_parent;
         if (leftSide) node_parent.leftTree = node.leftTree;
         else node_parent.rightTree = node.leftTree;
         rbt.level();
         putIt(null);
         return;
      }
      if (node.rightTree != rbt.sentinel) {
         // swap with child
         node.rightTree.color = node.rightTree.disp_color = Color.black;
         node.rightTree.parent = node_parent;
         if (leftSide) node_parent.leftTree = node.rightTree;
         else node_parent.rightTree = node.rightTree;
         rbt.level();
         putIt(null);
         return;
      }

      /* Now, we have determined that node is a black leaf node with no 
       * children.  The tree must have the same number of black nodes 
       * along any path from root to leaf.  We want to remove a black node, 
       * disrupting the number of black nodes along the path from the root 
       * to the current leaf.  To correct this, we must either shorten all 
       * other paths, or add a black node to the current path.  Then we can 
       * freely remove our black leaf.

       * While we are pointing to it, we will go ahead and delete the leaf 
       * and replace it with the sentinel (which is also black, so it won't 
       * affect the algorithm).
       */
      if (leftSide) node_parent.leftTree = rbt.sentinel; 
      else node_parent.rightTree = rbt.sentinel;
      rbt.level();
      putIt(tot);

      Dot sibling = (leftSide) ? node_parent.rightTree : node_parent.leftTree;
      Dot current = node;
      
      // Loop until the current node is red, or until we get to the root node.
      // (The root node's parent is the rootSentinel, which will have a NULL 
      // parent.)
      while (current.color == Color.black && node_parent.parent != null) {
         // If the sibling is red, we are unable to reduce the number of black
         // nodes in the sibling tree, and we can't increase the number of 
         // black nodes in our tree..  Thus we must do a rotation from the 
         // sibling tree to our tree to give us some extra (red) nodes to 
         // play with. This is Case 1 from the text
         if (sibling.color == Color.red) {
            node_parent.color = node_parent.disp_color = Color.red;
            sibling.color = sibling.disp_color = Color.black;
            rbt.level();
            putIt(tot);

            if (leftSide) {
               node_parent.leftRotate( );
               sibling = node_parent.rightTree;
            } else {
               node_parent.rightRotate( );
               sibling = node_parent.leftTree;
            }
            rbt.level();
            putIt(tot);

            continue;
         }
         // Sibling will be black here
         
         // If the sibling is black and both children are black, we have to
         //  reduce the black node count in the sibling's tree to match ours.
         // This is Case 2a from the text.
         if (sibling.rightTree.color == Color.black && 
             sibling.leftTree.color == Color.black) {
            if (sibling.color != Color.red) {
               sibling.color = sibling.disp_color = Color.red;
               rbt.level();
               putIt(tot);
            }

            // Now we move one level up the tree to continue fixing the
            // other branches.
            current = node_parent;
            node_parent = current.parent;
            leftSide = (node_parent.leftTree == current);
            sibling = (leftSide)? node_parent.rightTree : node_parent.leftTree;
            continue;
         }
         // Sibling will be black with 1 or 2 red children here
         
         // << Case 2b is handled by the while loop. >>

         // If one of the sibling's children are red, we again can't make the
         // sibling red to balance the tree at the parent, so we have to do a
         // rotation.  If the "near" nephew is red and the "far" nephew is
         // black, we need to do a "prep-slide" (aka "double rotation")
         // After doing a rotation and rearranging a few colors, the effect is
         // that we maintain the same number of black nodes per path on the 
         // far side of the parent, and we gain a black node on the current 
         // side, so we are done.
         // This is Case 4 from the text. (Case 3 is the double rotation)
         if (leftSide) {
            if (sibling.rightTree.color == Color.black) { // Case 3 from text
               sibling.rightSide_RightRotate( );
               sibling = node_parent.rightTree;
               rbt.level();
               putIt(tot);
            }
            // now Case 4 from the text
            if (sibling.rightTree.color != Color.black ||
                sibling.color != node_parent.color ||
                node_parent.color != Color.black) {
               sibling.rightTree.color = 
                  sibling.rightTree.disp_color = Color.black;
               sibling.color = sibling.disp_color = node_parent.color;
               node_parent.color = node_parent.disp_color = Color.black;
               rbt.level();
               putIt(tot);
            }

            current = node_parent;
            node_parent = current.parent;
            // I would have assigned to leftSide here,
            //   but we are exiting the function, so why bother?
            // leftSide= (Parent->Left == Current);
            if (node_parent.leftTree == current)
               current.leftSide_LeftRotate( );
            else
               current.rightSide_LeftRotate( );
            rbt.level();
            putIt(null);
            return;
         } else {
            if (sibling.leftTree.color == Color.black) { // Case 3 from text
               sibling.leftSide_LeftRotate( );
               sibling = node_parent.leftTree;
               rbt.level();
               putIt(tot);
            }
            // Case 4 from the text
            if (sibling.leftTree.color != Color.black ||
                sibling.color != node_parent.color ||
                node_parent.color != Color.black) {
               sibling.leftTree.color = 
                  sibling.leftTree.disp_color = Color.black;
               sibling.color = sibling.disp_color = node_parent.color;
               node_parent.color = node_parent.disp_color = Color.black;
               rbt.level();
               putIt(tot);
            }
            current = node_parent;
            node_parent = current.parent;
            // I would have assigned to leftSide here,
            // but we are exiting the function, so why bother?
            // leftSide = (Parent->Left == Current);
            if (node_parent.leftTree == current)
               current.leftSide_RightRotate();
            else
               current.rightSide_RightRotate();
            rbt.level();
            putIt(null);
            return;
         }
      }

      // Now, make the current node black (to fulfill Case 2b)
      // Case 4 will have exited directly out of the function.
      // If we stopped because we reached the top of the tree,
      //   the head is black anyway so don't worry about it.
      current.color = current.disp_color = Color.black;
      rbt.level();
      putIt(null);
      return;
   }
}

class RBTree {
   Dot sentinel, rootSentinel;

   RBTree() {
      sentinel = new Dot(sentinel);
      sentinel.leftTree = sentinel;
      sentinel.rightTree = sentinel;
      sentinel.parent = sentinel;
      sentinel.color = sentinel.disp_color = Color.black;
      sentinel.object = null;
      rootSentinel = new Dot(sentinel);
      rootSentinel.color = rootSentinel.disp_color = Color.black;
      rootSentinel.leftTree = sentinel;
      rootSentinel.rightTree = sentinel;
      rootSentinel.parent = null; // uniquely marks this as the root sentinel
      rootSentinel.object = null;
   }

   RBTree(RBTree tree) {
      sentinel = new Dot(sentinel);
      sentinel.leftTree = sentinel;
      sentinel.rightTree = sentinel;
      sentinel.parent = sentinel;
      sentinel.color = sentinel.disp_color = Color.black;
      sentinel.object = null;
      rootSentinel = new Dot(sentinel);
      rootSentinel.color = rootSentinel.disp_color = Color.black;
      rootSentinel.leftTree = sentinel;
      rootSentinel.rightTree = sentinel;
      rootSentinel.parent = null; // uniquely marks this as the root sentinel
      rootSentinel.object = null;
      rootSentinel.leftTree = 
         copyTree(tree.rootSentinel.leftTree, rootSentinel);
   }

   Dot copyTree (Dot dot, Dot parent) {
      Dot leftTree, rightTree, newdot;
      if (dot == dot.sentinel) return sentinel;
      if (dot.object == null) return sentinel;
      newdot = new Dot();
      newdot.parent = parent;
      newdot.color = newdot.disp_color = dot.color;
      newdot.left = dot.left;
      newdot.top = dot.top;
      newdot.level = dot.level;
      newdot.indent = dot.indent;
      newdot.object = dot.object;
      if (dot.leftTree == dot.sentinel) newdot.leftTree = sentinel;
      else newdot.leftTree = copyTree(dot.leftTree, newdot);
      if (dot.rightTree == dot.sentinel) newdot.rightTree = sentinel;
      else newdot.rightTree = copyTree(dot.rightTree, newdot);
      return newdot;
   }

   // Set up the array of Dots (we draw with those) and use current positions,
   // if objects remain, to allow a smooth transition back to position before
   // the undo was called.
   int traverse (Dot tree, Dot dot[], int ndots, Dot dott[], int ndotts) {
      if (tree == sentinel || tree == rootSentinel) return ndotts;
      dott[ndotts] = tree;
      for (int i=0 ; i < ndots ; i++) {
         if (dot[i] == null || dot[i].object == null) continue;
         if (dot[i].object == tree.object) {
            dott[ndotts].left = dot[i].left;
            dott[ndotts].top = dot[i].top;
            break;
         }
      }
      int n = traverse(tree.leftTree, dot, ndots, dott, ndotts+1);
      return traverse(tree.rightTree, dot, ndots, dott, n);
   }

   int setDots (Dot dot[], int ndots, Dot dott[], int ndotts) {
      return traverse(rootSentinel.leftTree, dot, ndots, dott, ndotts);
   }

   void reLevel(Dot dot, int ind, int lvl) {
      if (dot == sentinel) return;
      dot.level = lvl;
      dot.indent = ind;
      reLevel(dot.leftTree,  2*ind,   lvl+1);
      reLevel(dot.rightTree, 2*ind+1, lvl+1);
   }

   public void level() {
      reLevel(rootSentinel, 0, -1);
   }
}

// Where all the drawing takes place
class DotPanel extends Panel implements Runnable {
   RedBlack graph;
   Thread relaxer;
   Dot pick, saved_pick, deletingNode;
   boolean deleteNode = false, removingNode = false;
   int delayer = 50;

   DotPanel(RedBlack graph) {  
      this.graph = graph;  
   }

   public void run() {
      while (true) {
         repaint();
         try { Thread.sleep(delayer); }
         catch (InterruptedException e) {  break;  }
      }
   }

   Image offscreen;
   Dimension offscreensize;
   Graphics offgraphics;

   int left (Dot dot) {
      Dimension d = getSize();
      double wid = (double)d.width/(1+(1 << dot.level));
      return (int)(wid*(dot.indent+1)) + 15;
   }

   int top(Dot dot) {  return 20+dot.level*50 + 15;  }

   int offset = 28;

   public void paintDot(Graphics g, Dot dot, FontMetrics fm, int ox, int oy) {
      if (dot == null) return;
     
      int x  = left(dot);
      int y  = top(dot);
      int tx = (int)dot.left;
      int ty = (int)dot.top;

      String lbl = String.valueOf(dot.object.getIdent());
      int w = fm.stringWidth(lbl);
      int h = fm.getHeight();
      g.setColor(dot.disp_color);
      g.fillOval(tx+ox-offset, ty+oy, 30, 30);
      g.setColor(Color.white);
      g.drawString(lbl, tx+ox-offset-w/2+15, ty+oy+12+h/2);
      dot.left = (float)(.9*(dot.left-x) + x);
      dot.top = (float)(.9*(dot.top-y) + y);
   }

   public void paintPickedDot(Graphics g, Dot dot, FontMetrics fm) {
      if (dot == null) return;
      
      int tx = (int)dot.left;
      int ty = (int)dot.top;

      String lbl = String.valueOf(dot.object.getIdent());
      int w = fm.stringWidth(lbl);
      int h = fm.getHeight();
      g.setColor(dot.disp_color);
      g.fillOval(tx-offset, ty, 30, 30);
      g.setColor(Color.white);
      g.drawString(lbl, tx-offset-w/2+15, ty+12+h/2);
   }

   public void paintEdgesOfDot(Graphics g, Dot dot) {
      if (dot == null || 
          dot == graph.tree.sentinel || 
          dot == graph.tree.rootSentinel) return;
      
      g.setColor(Color.black);
      int x = (int)dot.left+15;
      int y = (int)dot.top+15;
      if (dot.leftTree != null && dot.leftTree != graph.tree.sentinel) {
         int lx = (int)dot.leftTree.left+15;
         int ly = (int)dot.leftTree.top+15;
         g.drawLine(x-offset,y,lx-offset,ly);
      }
      if (dot.rightTree != null && dot.rightTree != graph.tree.sentinel) {
         int rx = (int)dot.rightTree.left+15;
         int ry = (int)dot.rightTree.top+15;
         g.drawLine(x-offset,y,rx-offset,ry);
      }
   }

   public void update(Graphics g) {
      Dimension d = getSize();
      if ((offscreen == null) || (d.width != offscreensize.width) ||
          (d.height != offscreensize.height)) {
         offscreen = createImage(d.width, d.height);
         offscreensize = d;
         offgraphics = offscreen.getGraphics();
         offgraphics.setFont(getFont());
      }

      offgraphics.setColor(graph.getColor());
      offgraphics.fillRect(0, 0, d.width, d.height);
      FontMetrics fm = offgraphics.getFontMetrics();
      Dot dt[] = graph.dot;
      int nd  = graph.ndots;
      for (int i=0 ; i < nd ; i++)
         paintEdgesOfDot(offgraphics, dt[i]);
      for (int i=0 ; i < nd ; i++)
         if (dt[i] != pick) paintDot(offgraphics, dt[i], fm, 0, 0);
         else paintPickedDot(offgraphics, dt[i], fm);
      if (graph.newest != null)
         paintDot(offgraphics, graph.newest, fm, -30, -15);
      g.drawImage(offscreen, 0, 0, null);
   }

   public boolean mouseDown (Event evt, int x, int y) {
      Dot dot[] = graph.dot;
      for (int i=0 ; i < graph.ndots ; i++) {
         if (dot[i] == null) continue;
         if (x-dot[i].left < 0 && y-dot[i].top < 30 &&
             x > dot[i].left-30 && y > dot[i].top) {
            saved_pick = pick = dot[i];
            if (deleteNode == true) {
               deleteNode = false;
               deletingNode = pick;
               deletingNode.disp_color = Color.green;
               removingNode = true;
            }
         }
      }
      return true;
   }

   public boolean mouseDrag (Event evt, int x, int y) {
      if (pick != null) {
         pick.left = x+20;
         pick.top = y-10;
      }
      return true;
   }

   public boolean mouseUp (Event evt, int x, int y) {
      pick = null;
      return true;
   }

   public void start() { relaxer = new Thread(this);  relaxer.start(); }
}

public class RedBlack extends Applet {
   DotPanel panel;
   RBTree tree = null, saved_tree = null;
   Add adder = null;
   Prune pruner = null;
   Dot dot[] = new Dot[100], root = null, newest = null;
   int ndots = 0;
   Button addbutton, nextbutton, undobutton, colorit,
      restartbutton, tempbutton, delbutton;
   Choice speed;
   Color bgcolor = new Color(190,190,190);
   int number = 0;
   TextField value;
   Label label;

   public void init () {
      setBackground(bgcolor);
      setLayout(new BorderLayout());
      tree = new RBTree();
      panel = new DotPanel(this);
      panel.setBackground(bgcolor);
      add("Center", panel);
      Panel p = new Panel();
      p.setLayout(new GridLayout(2,1));
      Panel p1 = new Panel();
      p1.add(value = new TextField(5));
      p1.add(addbutton = new Button("Add Node"));
      p1.add(nextbutton = new Button("Next Step"));
      p1.add(delbutton = new Button("Delete Node"));
      p.add(p1);
      p1 = new Panel();
      p1.add(restartbutton = new Button("Restart"));
      p1.add(label = new Label("            ", Label.CENTER));
      p1.add(undobutton = new Button("Undo"));
      //p1.add(colorit = new Button("Color"));
      p1.add(speed = new Choice());
      speed.addItem("Fast");
      speed.addItem("Slow");
      speed.addItem("Crawl");
      p.add(p1);
      add("South", p);
      label.setBackground(bgcolor);
      value.setBackground(Color.white);
      initialize();
   }

   public void add_one (int number) {
      newest = new Dot(number, Color.blue);
      root = tree.rootSentinel.leftTree;
         
      while (true) {
         if (newest != null && adder == null) {
            if ((root = insertDot(newest, root)) == tree.sentinel) {
               dot[ndots++] = newest;
               adder = new Add(tree, newest, new IntInorderObject());
               TO to = (TO)adder.next();
               if (to == null) {
                  adder = null;
                  number = 0;
                  tree.level();
                  break;
               }
               tree.level();
               newest = root = null;
            } else if (root == null) { // Done adding a node
               newest = root = null;
               number = 0;
               tree.level();
               break;
            } // otherwise root is set to the next node down the tree
         } else if (adder != null) {  // Adding a node continuing...
            TO to = (TO)adder.next();
            if (to == null) {
               adder = null; 
               number = 0;
               tree.level();
               break;
            }
            tree.level();
         }
      }
   }

   public void quick_add () {
      try {
         int number = Integer.parseInt(value.getText());
         saved_tree = new RBTree(tree);
	 // if tree is empty add a node by hand
	 if (tree.rootSentinel.leftTree == tree.sentinel) {
	    tree.rootSentinel.leftTree = new Dot(tree, number, Color.black);
	    dot = new Dot[100];
	    ndots = 0;
	    dot[ndots++] = tree.rootSentinel.leftTree;
	    tree.level();
	 } else {
	    add_one (number);
	 }
	 value.setText("");
	 number = 0;
      } catch (Exception e) {}
      value.setText("");
      number = 0;
   }

   public void initialize () {
      restart();
      try {
         StringTokenizer t = new StringTokenizer(getParameter("args")," ");
         int number;
         if (t.hasMoreTokens()) {
            number = Integer.parseInt(t.nextToken());
            tree.rootSentinel.leftTree = new Dot(tree, number, Color.black);
            dot[ndots++] = tree.rootSentinel.leftTree;
         }
         while (t.hasMoreTokens()) {
            number = Integer.parseInt(t.nextToken());
            add_one (number);
         }
      } catch (Exception e) {}
   }

   public void colorIt() {
      if (panel.saved_pick != null) {
         if (panel.saved_pick.color == Color.red) {
            panel.saved_pick.color = Color.black;
            panel.saved_pick.disp_color = Color.black;
         } else {
            panel.saved_pick.color = Color.red;
            panel.saved_pick.disp_color = Color.red;
         }
      }
   }
                                                                 
   public void delete () {
      if (newest == null && adder == null && 
          !panel.removingNode && !panel.deleteNode && tree != null) {
         saved_tree = new RBTree(tree);
         panel.deleteNode = true;
         label.setBackground(Color.green);
         label.setText("Deleting");
      }
   }

   public void undo () {
      if (saved_tree == null) return;
      tree = new RBTree(saved_tree);
      Dot dott[] = new Dot[100];
      ndots = tree.setDots(dot, ndots, dott, 0);
      dot = dott;
      tree.level();
      newest = root = null;
      adder = null;
      pruner = null;
      panel.deleteNode = panel.removingNode = false;
      panel.saved_pick = null;
      label.setBackground(bgcolor);
   }

   public void restart () {
      saved_tree = null;
      tree = new RBTree();
      newest = root = null;
      adder = null;
      pruner = null;
      dot = new Dot[100];
      ndots = 0;
      panel.deleteNode = panel.removingNode = false;
      panel.saved_pick = null;
      label.setBackground(bgcolor);
      label.setText("            ");
      value.setText("");
      number = 0;
   }

   public void next () {
      if (newest != null && adder == null) {  // Adding a node starting...
         if ((root = insertDot(newest, root)) == tree.sentinel) {
            dot[ndots++] = newest;
            adder = new Add(tree, newest, new IntInorderObject());
            TO to = (TO)adder.next();
            if (to == null) {
               adder = null;
               label.setBackground(bgcolor);
               label.setText("            ");
               value.setText("");
               number = 0;
            }
            tree.level();
            newest = root = null;
         } else if (root == null) { // Done adding a node
            newest = root = null;
            label.setBackground(bgcolor);
            label.setText("            ");
            value.setText("");
            number = 0;
         } // otherwise root is set to the next node down the tree
      } else if (adder != null) {  // Adding a node continuing...
         TO to = (TO)adder.next();
         if (to == null) {
            adder = null; 
            label.setBackground(bgcolor);
            label.setText("            ");
            value.setText("");
            number = 0;
         }
         tree.level();
      } else if (panel.removingNode) {  // Removing a node
         if (pruner == null) {
            panel.saved_pick.disp_color = panel.saved_pick.color;
            deleteDot(panel.saved_pick);
            pruner = new Prune(tree, panel.saved_pick);
         }
         TO to = (TO)pruner.next();
         if (to == null) {
            pruner = null; 
            label.setBackground(bgcolor);
            label.setText("            ");
            value.setText("");
            number = 0;
            panel.removingNode = false;
         }
         tree.level();
      }
   }

   public void add () {
      if (newest == null && adder == null && 
          !panel.removingNode && !panel.deleteNode) {
         saved_tree = new RBTree(tree);
         try {
            number = Integer.parseInt(value.getText());
            newest = new Dot(number, Color.blue);
            root = tree.rootSentinel.leftTree;
            label.setBackground(Color.yellow);
            label.setText("Adding");
         } catch (Exception e) {
            value.setText("-=*=-");
         }
      }
   }

   public void fast () { panel.delayer = 50; }
   public void slow () { panel.delayer = 100; }
   public void crawl () { panel.delayer = 150; }

   public boolean action (Event evt, Object obj) {
      if (evt.target.equals(delbutton)) delete();
      else if (evt.target.equals(undobutton)) undo();
      else if (evt.target.equals(restartbutton)) restart();
      else if (evt.target.equals(nextbutton)) next();
      else if (evt.target.equals(addbutton)) add();
      else if (evt.target.equals(value)) quick_add();
      else if (evt.target.equals(speed)) {
         if (speed.getSelectedItem().equals("Fast")) fast();
         else if (speed.getSelectedItem().equals("Slow")) slow();
         else if (speed.getSelectedItem().equals("Crawl")) crawl();
      }
      //else if (evt.target.equals(colorit)) colorIt();
      return super.action(evt, obj);
   }

   public void deleteDot(Dot d) {
      int m;
      for (int k=0 ; k < ndots ; k++) {
         if (dot[k] == null) continue;
         if (dot[k] == d) {
            dot[k] = null;
            break;
         }
      }
   }

   public Dot insertDot(Dot dot, Dot root) {
      if (root == null) return null;
      if (root == tree.sentinel) return tree.sentinel;
      
      /*  Uncomment to prevent duplicate data objects  */
      /*
      if (dot.object.getValue() == root.object.getValue()) {
         return null;
      } else */
      if (dot.object.getValue() <= root.object.getValue()) {
         dot.level = root.level+1;
         dot.indent = 2*root.indent;
         if (root.leftTree == null || root.leftTree == tree.sentinel) {
            return tree.sentinel;
         }
         return root.leftTree;
      } else {
         dot.level = root.level+1;
         dot.indent = 2*root.indent+1;
         if (root.rightTree == null || root.rightTree == tree.sentinel) {
            return tree.sentinel;
         }
         return root.rightTree;
      }
   }

   public void start() {  panel.start();  }

   public Color getColor() {  return bgcolor;  }
}
