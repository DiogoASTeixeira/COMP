/* Generated By:JJTree&JavaCC: Do not edit this line. Calculator.java */
 public class Calculator/*@bgen(jjtree)*/implements CalculatorTreeConstants, CalculatorConstants {/*@bgen(jjtree)*/
  protected static JJTCalculatorState jjtree = new JJTCalculatorState();public static void main(String args[]) throws ParseException
     {
        Calculator myCalc = new Calculator(System.in);
        SimpleNode root = myCalc.Expression();
        System.out.println("Expression value: "+myCalc.eval(root));
        root.dump("");
     }


int eval(SimpleNode node) {
    if(node.jjtGetNumChildren() == 0) // leaf node with integer value
        return node.val;
    else if(node.jjtGetNumChildren() == 1) //only one child
        return this.eval((SimpleNode) node.jjtGetChild(0));

    SimpleNode lhs = (SimpleNode) node.jjtGetChild(0); //left child
    SimpleNode rhs = (SimpleNode) node.jjtGetChild(1); //right child

    switch(node.id) {
        case CalculatorTreeConstants.JJTADD : return eval(lhs) + eval(rhs);
        case CalculatorTreeConstants.JJTSUB : return eval(lhs) - eval(rhs);
        case CalculatorTreeConstants.JJTMUL : return eval(lhs) * eval(rhs);
        case CalculatorTreeConstants.JJTDIV : return eval(lhs) / eval(rhs);
        default: //abort
            System.out.println("Illegal operator!");
            System.exit(1);
    }
    return  0;
}

  static final public SimpleNode Expression() throws ParseException {
                           /*@bgen(jjtree) Expression */
  SimpleNode jjtn000 = new SimpleNode(JJTEXPRESSION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      Expr1(1);
      jj_consume_token(LF);
                     jjtree.closeNodeScope(jjtn000, true);
                     jjtc000 = false;
                    {if (true) return jjtn000;}
    } catch (Throwable jjte000) {
       if (jjtc000) {
         jjtree.clearNodeScope(jjtn000);
         jjtc000 = false;
       } else {
         jjtree.popNode();
       }
       if (jjte000 instanceof RuntimeException) {
         {if (true) throw (RuntimeException)jjte000;}
       }
       if (jjte000 instanceof ParseException) {
         {if (true) throw (ParseException)jjte000;}
       }
       {if (true) throw (Error)jjte000;}
    } finally {
       if (jjtc000) {
         jjtree.closeNodeScope(jjtn000, true);
       }
    }
    throw new Error("Missing return statement in function");
  }

  static final public void Expr1(int sign) throws ParseException {
    Expr2(sign);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CROSS:
    case MINUS:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CROSS:
        jj_consume_token(CROSS);
                     SimpleNode jjtn001 = new SimpleNode(JJTADD);
                     boolean jjtc001 = true;
                     jjtree.openNodeScope(jjtn001);
        try {
          Expr2(sign);
        } catch (Throwable jjte001) {
                     if (jjtc001) {
                       jjtree.clearNodeScope(jjtn001);
                       jjtc001 = false;
                     } else {
                       jjtree.popNode();
                     }
                     if (jjte001 instanceof RuntimeException) {
                       {if (true) throw (RuntimeException)jjte001;}
                     }
                     if (jjte001 instanceof ParseException) {
                       {if (true) throw (ParseException)jjte001;}
                     }
                     {if (true) throw (Error)jjte001;}
        } finally {
                     if (jjtc001) {
                       jjtree.closeNodeScope(jjtn001,  2);
                     }
        }
        break;
      case MINUS:
        jj_consume_token(MINUS);
                     SimpleNode jjtn002 = new SimpleNode(JJTSUB);
                     boolean jjtc002 = true;
                     jjtree.openNodeScope(jjtn002);
        try {
          Expr2(sign);
        } catch (Throwable jjte002) {
                     if (jjtc002) {
                       jjtree.clearNodeScope(jjtn002);
                       jjtc002 = false;
                     } else {
                       jjtree.popNode();
                     }
                     if (jjte002 instanceof RuntimeException) {
                       {if (true) throw (RuntimeException)jjte002;}
                     }
                     if (jjte002 instanceof ParseException) {
                       {if (true) throw (ParseException)jjte002;}
                     }
                     {if (true) throw (Error)jjte002;}
        } finally {
                     if (jjtc002) {
                       jjtree.closeNodeScope(jjtn002,  2);
                     }
        }
        break;
      default:
        jj_la1[0] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[1] = jj_gen;
      ;
    }
  }

  static final public void Expr2(int sign) throws ParseException {
    Expr3(sign);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case STAR:
    case DASH:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STAR:
        jj_consume_token(STAR);
                    SimpleNode jjtn001 = new SimpleNode(JJTMUL);
                    boolean jjtc001 = true;
                    jjtree.openNodeScope(jjtn001);
        try {
          Expr3(1);
        } catch (Throwable jjte001) {
                    if (jjtc001) {
                      jjtree.clearNodeScope(jjtn001);
                      jjtc001 = false;
                    } else {
                      jjtree.popNode();
                    }
                    if (jjte001 instanceof RuntimeException) {
                      {if (true) throw (RuntimeException)jjte001;}
                    }
                    if (jjte001 instanceof ParseException) {
                      {if (true) throw (ParseException)jjte001;}
                    }
                    {if (true) throw (Error)jjte001;}
        } finally {
                    if (jjtc001) {
                      jjtree.closeNodeScope(jjtn001,  2);
                    }
        }
        break;
      case DASH:
        jj_consume_token(DASH);
                    SimpleNode jjtn002 = new SimpleNode(JJTDIV);
                    boolean jjtc002 = true;
                    jjtree.openNodeScope(jjtn002);
        try {
          Expr3(1);
        } catch (Throwable jjte002) {
                    if (jjtc002) {
                      jjtree.clearNodeScope(jjtn002);
                      jjtc002 = false;
                    } else {
                      jjtree.popNode();
                    }
                    if (jjte002 instanceof RuntimeException) {
                      {if (true) throw (RuntimeException)jjte002;}
                    }
                    if (jjte002 instanceof ParseException) {
                      {if (true) throw (ParseException)jjte002;}
                    }
                    {if (true) throw (Error)jjte002;}
        } finally {
                    if (jjtc002) {
                      jjtree.closeNodeScope(jjtn002,  2);
                    }
        }
        break;
      default:
        jj_la1[2] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[3] = jj_gen;
      ;
    }
  }

  static final public void Expr3(int sign) throws ParseException {
                              Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INTEGER:
      t = jj_consume_token(INTEGER);
       SimpleNode jjtn001 = new SimpleNode(JJTTERM);
       boolean jjtc001 = true;
       jjtree.openNodeScope(jjtn001);
      try {
       jjtree.closeNodeScope(jjtn001, true);
       jjtc001 = false;
        jjtn001.val = sign * Integer.parseInt(t.image);
      } finally {
       if (jjtc001) {
         jjtree.closeNodeScope(jjtn001, true);
       }
      }
      break;
    case MINUS:
      jj_consume_token(MINUS);
      Expr3(-1);
      break;
    case LP:
      jj_consume_token(LP);
      Expr1(1);
      jj_consume_token(RP);
      break;
    case MINUS_LP:
      jj_consume_token(MINUS_LP);
      Expr1(-1);
      jj_consume_token(RP);
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public CalculatorTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[5];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x60,0x60,0x180,0x180,0x1250,};
   }

  /** Constructor with InputStream. */
  public Calculator(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Calculator(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new CalculatorTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Calculator(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new CalculatorTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Calculator(CalculatorTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(CalculatorTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[13];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 5; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 13; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

 }
