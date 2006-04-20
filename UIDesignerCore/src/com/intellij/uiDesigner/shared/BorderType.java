/*
 * Copyright 2000-2005 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.uiDesigner.shared;

import com.intellij.uiDesigner.compiler.UnexpectedFormElementException;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Font;

/**
 * @author Vladimir Kondratyev
 */
public final class BorderType {
  public static final BorderType NONE = new BorderType("none", "None",null,null);
  public static final BorderType BEVEL_LOWERED = new BorderType("bevel-lowered", "Bevel Lowered", BorderFactory.createLoweredBevelBorder(), "createLoweredBevelBorder");
  public static final BorderType BEVEL_RAISED = new BorderType("bevel-raised", "Bevel Raised", BorderFactory.createRaisedBevelBorder(), "createRaisedBevelBorder");
  public static final BorderType ETCHED = new BorderType("etched", "Etched", BorderFactory.createEtchedBorder(), "createEtchedBorder");
  public static final BorderType LINE = new BorderType("line", "Line", BorderFactory.createLineBorder(Color.BLACK), "createLineBorder");

  private final String myId;
  private final String myName;
  private final Border myBorder;
  private final String myBorderFactoryMethodName;

  private BorderType(final String id, final String name, final Border border, final String borderFactoryMethodName) {
    myId=id;
    myName=name;
    myBorder=border;
    myBorderFactoryMethodName = borderFactoryMethodName;
  }

  public String getId(){
    return myId;
  }

  public String getName(){
    return myName;
  }

  public Border createBorder(final String title,
                             final int titleJustification,
                             final int titlePosition,
                             final Font titleFont,
                             final Color titleColor){
    if (title != null) {
      return BorderFactory.createTitledBorder(myBorder, title, titleJustification, titlePosition, titleFont, titleColor);
    }
    else {
      return myBorder;
    }
  }

  public String getBorderFactoryMethodName(){
    return myBorderFactoryMethodName;
  }

  public boolean equals(final Object o){
    if (o instanceof BorderType){
      return myId.equals(((BorderType)o).myId);
    }
    return false;
  }

  public int hashCode(){
    return 0;
  }

  public static BorderType valueOf(final String name){
    if(NONE.getId().equals(name)){
      return NONE;
    }
    else if(BEVEL_LOWERED.getId().equals(name)){
      return BEVEL_LOWERED;
    }
    else if(BEVEL_RAISED.getId().equals(name)){
      return BEVEL_RAISED;
    }
    else if(ETCHED.getId().equals(name)){
      return ETCHED;
    }
    else if (LINE.getId().equals(name)) {
      return LINE;
    }
    else{
      throw new UnexpectedFormElementException("unknown type: "+name);
    }
  }
}
