import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.Map;
import java.util.TreeMap;

/* Generated By:JJTree: Do not edit this line. SimpleNode.java Version 6.0 */
/* JavaCCOptions:MULTI=false,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public class SimpleNode implements Node {

  protected Node parent;
  protected Node[] children;
  protected int id;
  protected Object value;
  protected NewJava parser;
  protected String symbol;
  protected int line;
  protected int column;

  public SimpleNode(int i) {
    id = i;
  }

  public SimpleNode(NewJava p, int i) {
    this(i);
    parser = p;
  }

  public void jjtOpen() {
  }

  public void jjtClose() {
  }

  public void jjtSetParent(Node n) {
    parent = n;
  }

  public Node jjtGetParent() {
    return parent;
  }

  public void jjtAddChild(Node n, int i) {
    if (children == null) {
      children = new Node[i + 1];
    } else if (i >= children.length) {
      Node c[] = new Node[i + 1];
      System.arraycopy(children, 0, c, 0, children.length);
      children = c;
    }
    children[i] = n;
  }

  public Node jjtGetChild(int i) {
    return children[i];
  }

  public int jjtGetNumChildren() {
    return (children == null) ? 0 : children.length;
  }

  public void jjtSetValue(Object value) {
    this.value = value;
  }

  public Object jjtGetValue() {
    return value;
  }

  /*
   * You can override these two methods in subclasses of SimpleNode to customize
   * the way the node appears when the tree is dumped. If your output uses more
   * than one line you should override toString(String), otherwise overriding
   * toString() is probably all you need to do.
   */

  public String toString() {
    return NewJavaTreeConstants.jjtNodeName[id];
  }

  public String toString(String prefix) {
    return prefix + toString();
  }

  /*
   * Override this method if you want to customize how the node dumps out its
   * children.
   */

  public void dump(String prefix) {
    System.out.print(toString(prefix));
    if (this.symbol != null)
      System.out.print(" " + this.symbol);
    System.out.println();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        SimpleNode n = (SimpleNode) children[i];
        if (n != null) {
          n.dump(prefix + " ");
        }
      }
    }
  }

  public int getId() {
    return id;
  }

  public String getSymbol() {
    return symbol;
  }

  public Object getValue() {
    return value;
  }

  public int getLineNumber() {
    return line;
  }

  public void setLineNumber(int lineNumber) {
    this.line = lineNumber;
  }

  public int getColumnNumber() {
    return column;
  }

  public void addInitializedVar(SymbolTable data, int functionNum, String symbol) {
    if (!data.initializedVariables.isEmpty()) {
      if (data.initializedVariables.get(functionNum) != null) {
        if (!data.initializedVariables.get(functionNum).contains(symbol)) {
          ArrayList<String> vars = data.initializedVariables.get(functionNum);
          vars.add(symbol);
          data.initializedVariables.replace(functionNum, vars);
        }
      } else {
        ArrayList<String> vars = new ArrayList<>();
        vars.add(symbol);
        data.initializedVariables.put(functionNum, vars);
      }
    } else {
      ArrayList<String> vars = new ArrayList<>();
      vars.add(symbol);
      data.initializedVariables.put(functionNum, vars);
    }
  }

  @Override
  public Object visit(SymbolTable data, int functionNum) {
    SemanticalError error;

    if (id == NewJava.JJTVAR || id == NewJava.JJTTEXT) {
      String name = (String) this.getSymbol();

      String type = data.checkIfExists(name, functionNum); // checkar locais


      if (type.equals("error")) {
        type = data.searchParam(name, functionNum); // checkar params
      }

      if (type.equals("error")) {
        type = data.checkIfExistGlobals(name); // checkar globais
        if (!type.equals("error")) {
          data.initializeGlobalVariable(this.getSymbol());
        }
      }


      if (!type.contains("/")) { // variable has unique name
        if ((!type.equals("int") && !type.equals("boolean") && !type.equals("int[]")) || type.equals("error")) {
          if (this.jjtGetNumChildren() > 0) {
            if (!Main.tables.containsKey(this.jjtGetChild(0).getSymbol())) { // checkar se é outra classe
              error = new SemanticalError("UNKNOWN_SYMBOL", data.filePath, this.jjtGetChild(0).getLineNumber(), ((SimpleNode) this.jjtGetChild(0)).getColumnNumber());
              error.printError(data.className, "class", ((SimpleNode) this.jjtGetChild(0)).getSymbol());
              return "error";
            } else {
              return this.jjtGetChild(0).getSymbol();
            }
          }
        }
      } else {
        String[] responseArr = type.split("/");

        int lineNum = Integer.parseInt(responseArr[0]);

        // node with repeated variable name
        if (lineNum == this.line) {
          error = new SemanticalError("MULTIPLE_DECLARATION", data.filePath, lineNum, column);
          error.printError(responseArr, symbol);
          return "error";
        }
      }

      if (this.jjtGetNumChildren() != 0 && this.jjtGetChild(0).getId() == NewJava.JJTARRINDEX) {
        this.jjtGetChild(0).visit(data, functionNum);
      }

      return type.toString();
    }

    if (id == NewJava.JJTTRUE || id == NewJava.JJTFALSE) {
      return "boolean";
    }

    if (id == NewJava.JJTVAL) {
      return "int";
    }

    if (id == NewJava.JJTNEW) {
      if (symbol.equals("int[]")) {
        Map<String, Integer> arraySize = new TreeMap<String, Integer>();
        //se o indice nao for uma constante int
        try{
          arraySize.put(jjtGetParent().jjtGetChild(0).getSymbol(), Integer.parseInt(this.jjtGetChild(0).getSymbol()));
        }catch(Exception nfe){
          arraySize.put(jjtGetParent().jjtGetChild(0).getSymbol(), -1);
        }
        data.initializedArrays.put(functionNum, arraySize);
      }
      return symbol;
    }

    if (id == NewJava.JJTARRINDEX) {
      if (!data.initializedArrays.isEmpty() && data.initializedArrays.get(functionNum) != null
          && data.initializedArrays.get(functionNum).get(this.jjtGetParent().getSymbol()) != null) {
        int length = data.initializedArrays.get(functionNum).get(this.jjtGetParent().getSymbol());
        int index;

        if (this.jjtGetChild(0).getId() == NewJava.JJTVAL) {
          index = Integer.parseInt(this.jjtGetChild(0).getSymbol());

          if ((index >= length) && (length>-1)) {
            error = new SemanticalError("OUT_OF_BOUNDS", data.filePath, this.jjtGetChild(0).getLineNumber(), this.jjtGetChild(0).getColumnNumber());
            error.printError(index, length);
            return "error";
          }
        }
      }
      return "int";
    }

    if (id == NewJava.JJTTHIS) {
      return "this";
    }

    if ((id == NewJava.JJTOP2) || (id == NewJava.JJTOP3) || (id == NewJava.JJTOP4) || (id == NewJava.JJTOP5)) {
      boolean ok = checkOpType(this, data, functionNum);
      if (!ok) {
        error = new SemanticalError("BAD_OPERAND_TYPES", data.filePath, line, column);
        error.printError(symbol);
        return "error";
      }

      return "int";
    }

    if (id == NewJava.JJTARGS) {
      for (int i = 0; i < this.jjtGetNumChildren(); i++) {
        addInitializedVar(data, functionNum, this.jjtGetChild(i).getSymbol());
      }
    }

    if (id == NewJava.JJTASSIGN) {
      // Lado esquerdo
      String identifierType = (String) this.jjtGetChild(0).visit(data, functionNum);

      // Lado direito assign
      String expressionType = (String) this.jjtGetChild(1).visit(data, functionNum);

      if (identifierType.equals("int[]") && !expressionType.equals("int[]")) {
        identifierType = "int";
      }

      if (!identifierType.equals("int[]") && expressionType.equals("int[]")) {
        expressionType = "int";
      }

      // previous semantic error: already defined variable
      if (((String) identifierType).contains("/")) {
        return "error";
      }

      // Lado esquerdo assign
      if (identifierType.equals("error")) {
        error = new SemanticalError("UNKNOWN_SYMBOL", data.filePath, this.jjtGetChild(0).getLineNumber(), ((SimpleNode) this.jjtGetChild(0)).getColumnNumber());
        error.printError(data.className, "variable", ((SimpleNode) this.jjtGetChild(0)).getSymbol());
        return "error";
      }

      // previous semantic error: already defined variable
      if (((String) expressionType).contains("/")) {
        return "error";
      }

      if ((!identifierType.equals(expressionType)) &&
      !(identifierType.equals("int") && expressionType.equals("int[]")) && expressionType.equals("int[]") &&
      !(identifierType.equals("boolean") && expressionType.equals("int")) && expressionType.equals("int")) {
        error = new SemanticalError("INCOMPATIBLE_TYPES", data.filePath, this.jjtGetChild(0).getLineNumber(), ((SimpleNode) this.jjtGetChild(1)).getColumnNumber());
        error.printError(expressionType, identifierType);
        return "error";
      }

      addInitializedVar(data, functionNum, this.jjtGetChild(0).getSymbol());
    }

    // Function type must match return type
    if (this.id == NewJava.JJTRETURN) {
      String type = (String) ((SimpleNode) this.jjtGetChild(0)).visit(data, functionNum);
      String dataType = data.getReturn(functionNum);

      if ((!type.equals(dataType)) &&
      !(type.equals("int") && dataType.equals("int[]")) && dataType.equals("int[]") &&
      !(type.equals("boolean") && dataType.equals("int")) && dataType.equals("int")) {
        error = new SemanticalError("INCOMPATIBLE_TYPES", data.filePath, this.jjtGetChild(0).getLineNumber(), ((SimpleNode) this.jjtGetChild(0)).getColumnNumber());
        error.printError(type, dataType);
        return "error";
      }

      if (this.jjtGetChild(0).getId() == NewJava.JJTTEXT && (data.initializedVariables.isEmpty() || data.initializedVariables.get(functionNum) == null || !data.initializedVariables.get(functionNum).contains(this.jjtGetChild(0).getSymbol()))) {
        error = new SemanticalError("NO_INITIALIZATION", data.filePath, this.jjtGetChild(0).getLineNumber(), this.jjtGetChild(0).getColumnNumber());
        error.printError(this.jjtGetChild(0).getSymbol());
        return "error";
      }
    }

    // check function calls (right param number or function exists)
    if (id == NewJava.JJTFULLSTOP) {
      SimpleNode leftSide = (SimpleNode) this.jjtGetChild(0);

      if (this.jjtGetChild(0).getId() == NewJava.JJTOP2 || this.jjtGetChild(0).getId() == NewJava.JJTOP3) {
        leftSide = (SimpleNode) this.jjtGetChild(0).jjtGetChild(1);
      }

      SimpleNode rightSide = (SimpleNode) this.jjtGetChild(1);

      if (rightSide.getSymbol().equals("length")) {
        return "int";
      }

      String type = (String) leftSide.visit(data, functionNum);


      if (leftSide.getId()!= NewJava.JJTNEW && !type.equals("this") && !type.equals("error") && (data.initializedVariables.isEmpty() || data.initializedVariables.get(functionNum) == null || !data.initializedVariables.get(functionNum).contains(leftSide.symbol))) {
        error = new SemanticalError("NO_INITIALIZATION", data.filePath, leftSide.getLineNumber(), leftSide.getColumnNumber());
        error.printError(leftSide.symbol);
        return "error";
      }

      if (type.equals("this")) {
        type = data.className;
      }

      // class
      if (Main.tables.containsKey(type)) {
        SymbolTable tableAux = Main.tables.get(type);

        for (SymbolTableEntry var : tableAux.entries) {
          if (var.name.equals(rightSide.getSymbol())) {

            if (var.params.size() != rightSide.jjtGetNumChildren()) {
              error = new SemanticalError("WRONG_METHOD_ARGS", data.filePath, rightSide.getLineNumber(), rightSide.getColumnNumber());
              error.printError(var.name, data.className, var.params, rightSide, data, functionNum);
              return "error";
            } else {
              for (int i = 0; i < var.params.size(); i++) {
                String typeArg = (String) ((SimpleNode) rightSide.jjtGetChild(i)).visit(data, functionNum);
                //e
                if (!var.params.get(i).type.equals(typeArg) &&
                !(var.params.get(i).type.equals("int") && typeArg.equals("int[]")) && typeArg.equals("int[]") &&
                !(var.params.get(i).type.equals("boolean") && typeArg.equals("int")) && typeArg.equals("int")){
                  error = new SemanticalError("INCOMPATIBLE_TYPES", data.filePath, leftSide.getLineNumber(), ((SimpleNode) rightSide.jjtGetChild(i)).getColumnNumber());
                  error.printError(typeArg, var.params.get(i).type);
                  return "error";
                }
                if (rightSide.jjtGetChild(i).getId() == NewJava.JJTTEXT && (data.initializedVariables.isEmpty() || data.initializedVariables.get(functionNum) == null || !data.initializedVariables.get(functionNum).contains(rightSide.jjtGetChild(i).getSymbol()))) {
                  error = new SemanticalError("NO_INITIALIZATION", data.filePath,rightSide.jjtGetChild(i).getLineNumber(), rightSide.jjtGetChild(i).getColumnNumber());
                  error.printError(rightSide.jjtGetChild(i).getSymbol());
                  return "error";
                }
              }
            }

            return var.returnDescriptor;
          }
        }


        error = new SemanticalError("UNKNOWN_SYMBOL",data.filePath, leftSide.getLineNumber(), rightSide.getColumnNumber());
        error.printError(data.className, "method", rightSide.getSymbol());
        return "error";
      }

        if (rightSide.jjtGetNumChildren() != 0 && rightSide.jjtGetChild(0).getId() == NewJava.JJTFULLSTOP) {
          return rightSide.jjtGetChild(0).visit(data, functionNum);
        }

        if (type.equals("this")){
          return rightSide.visit(data, functionNum);
        }
        if(rightSide.jjtGetNumChildren() == 0 && this.jjtGetParent().getId() != NewJava.JJTMAIN && this.jjtGetParent().getId() != NewJava.JJTFUNCTION)
          return this.jjtGetParent().jjtGetChild(0).visit(data, functionNum);
        else
          return "int";
    }

    if (id == NewJava.JJTIF || id == NewJava.JJTWHILE) {
      String conditionType = (String) ((SimpleNode) this.jjtGetChild(0)).visit(data, functionNum);

      if (!conditionType.equals("boolean") && !conditionType.equals("int") && !conditionType.equals("error")
          && NewJava.JJTOP2 != this.jjtGetChild(0).getId() && NewJava.JJTOP3 != this.jjtGetChild(0).getId() && NewJava.JJTNOT != this.jjtGetChild(0).getId()) {
        error = new SemanticalError("INCOMPATIBLE_TYPES", data.filePath, this.jjtGetChild(0).getLineNumber(),
            ((SimpleNode) this.jjtGetChild(0)).getColumnNumber());
        error.printError(conditionType, "boolean");
        return "error";
      }

      if (this.jjtGetChild(0).getId() == NewJava.JJTTEXT
          && (data.initializedVariables.isEmpty() || data.initializedVariables.get(functionNum) == null
              || !data.initializedVariables.get(functionNum).contains(this.jjtGetChild(0).getSymbol()))) {
        error = new SemanticalError("NO_INITIALIZATION", data.filePath, this.jjtGetChild(0).getLineNumber(),
            this.jjtGetChild(0).getColumnNumber());
        error.printError(this.jjtGetChild(0).getSymbol());
        return "error";
      }

      for (int i = 1; i < this.jjtGetNumChildren(); i++) {
        this.jjtGetChild(i).visit(data, functionNum);
      }
    }

    if (id == NewJava.JJTELSE) {
      for (int i = 0; i < this.jjtGetNumChildren(); i++) {
        this.jjtGetChild(i).visit(data, functionNum);
      }
    }

    return "";
  }

  private boolean checkOpType(SimpleNode startOP, SymbolTable data, int functionNum) {
    SemanticalError error;

    if (startOP.jjtGetNumChildren() != 2) {
      return false;
    }

    // check if variable is initialized
    if (startOP.jjtGetChild(0).getId() == NewJava.JJTTEXT
        && (data.initializedVariables.isEmpty() || data.initializedVariables.get(functionNum) == null
            || !data.initializedVariables.get(functionNum).contains(startOP.jjtGetChild(0).getSymbol()))) {
      error = new SemanticalError("NO_INITIALIZATION", data.filePath, startOP.jjtGetChild(0).getLineNumber(),
          startOP.jjtGetChild(0).getColumnNumber());
      error.printError(startOP.jjtGetChild(0).getSymbol());
      return true;
    }

    // ramo esquerdo
    // se nao for op, verifica se e inteiro
    if (startOP.jjtGetChild(0).getId() != NewJava.JJTOP2 && startOP.jjtGetChild(0).getId() != NewJava.JJTOP3
        && startOP.jjtGetChild(0).getId() != NewJava.JJTOP4 && startOP.jjtGetChild(0).getId() != NewJava.JJTOP5) {
      String type = (String) startOP.jjtGetChild(0).visit(data, functionNum);

      if (!type.equals("int") && !type.equals("int[]") && !type.equals("boolean")) {
        return false;
      }
      // se for op, verifica se e vailda
    } else if (!checkOpType((SimpleNode) startOP.jjtGetChild(0), data, functionNum)) {
      return false;
    }

    if (startOP.jjtGetChild(1).getId() == NewJava.JJTTEXT
        && (data.initializedVariables.isEmpty() || data.initializedVariables.get(functionNum) == null
            || !data.initializedVariables.get(functionNum).contains(startOP.jjtGetChild(1).getSymbol()))) {
      error = new SemanticalError("NO_INITIALIZATION", data.filePath, startOP.jjtGetChild(1).getLineNumber(),
          startOP.jjtGetChild(1).getColumnNumber());
      error.printError(startOP.jjtGetChild(1).getSymbol());
      return true;
    }

    // ramo direito
    if (startOP.jjtGetChild(1).getId() != NewJava.JJTOP2 && startOP.jjtGetChild(1).getId() != NewJava.JJTOP3
        && startOP.jjtGetChild(1).getId() != NewJava.JJTOP4 && startOP.jjtGetChild(1).getId() != NewJava.JJTOP5) {
      String type = (String) startOP.jjtGetChild(1).visit(data, functionNum);

      if (!type.equals("int") && !type.equals("int[]") && !type.equals("boolean")) {
        return false;
      }
    } else if (!checkOpType((SimpleNode) startOP.jjtGetChild(1), data, functionNum)) {
      return false;
    }

    return true;
  }

}

/*
 * JavaCC - OriginalChecksum=9eef391808ffa9c1f856b164b9ceaad4 (do not edit this
 * line)
 */