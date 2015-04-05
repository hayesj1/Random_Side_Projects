package userIO.io.graphicalPopups;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JOptionPane;
/**
 * Shortcut Class with static methods for calling {@link JOptionPane}'s dialog methods
 * 
 * @author hayesj3
 */
public class Popup {

	// Input dialog
	public static Object getInput(String title, Object message) {
		return Popup.getInput(title, message, JOptionPane.QUESTION_MESSAGE);
	}
	public static Object getInput(String title, Object message, int messageType) {
		return Popup.getInput(title, message, messageType, null);
	}
	public static Object getInput(String title, Object message, int messageType, Component parentFrame) {
		return Popup.getInput(title, message, messageType, parentFrame, null);
	}
	public static Object getInput(String title, Object message, int messageType, Component parentFrame, Icon icon) {
		return Popup.getInput(title, message, messageType, parentFrame, icon, "");
	}
	public static Object getInput(String title, Object message, int messageType, Component parentFrame, Icon icon, Object initialValue) {
		return JOptionPane.showInputDialog(parentFrame, message, title, messageType, icon, null, initialValue);		
	}
	
	// Confirm dialog
	public static int getConfirmChoice(String title, Object message, int optionType) {
		return Popup.getConfirmChoice(title, message, optionType, JOptionPane.QUESTION_MESSAGE);
	}
	public static int getConfirmChoice(String title, Object message, int optionType, int messageType) {
		return Popup.getConfirmChoice(title, message, optionType, messageType, null);
	}
	public static int getConfirmChoice(String title, Object message, int optionType, int messageType, Component parentFrame) {
		return Popup.getConfirmChoice(title, message, optionType, messageType, parentFrame, null);
	}
	public static int getConfirmChoice(String title, Object message, int optionType, int messageType, Component parentFrame, Icon icon) {
		return JOptionPane.showConfirmDialog(parentFrame, message, title, optionType, messageType, icon);		
	}
	
	// Info dialog
	public static void showInfo(String title, Object message) {
		Popup.showInfo(title, message, JOptionPane.INFORMATION_MESSAGE);
	}
	public static void showInfo(String title, Object message, int messageType) {
		Popup.showInfo(title, message, messageType, null);
	}
	public static void showInfo(String title, Object message, int messageType, Component parentFrame) {
		Popup.showInfo(title, message, messageType, parentFrame, null);
	}
	public static void showInfo(String title, Object message, int messageType, Component parentFrame, Icon icon) {
		JOptionPane.showMessageDialog(parentFrame, message, title, messageType, icon);		
	}
	
	// Option dialog
	public static int showOptions(String title, Object message, int optionType, int messageType, Object[] options) {
		return Popup.showOptions(title, message, optionType, messageType, options, null);		
	}
	public static int showOptions(String title, Object message, int optionType, int messageType, Object[] options, Component parentFrame) {
		return Popup.showOptions(title, message, optionType, messageType, options, parentFrame, null);		
	}
	public static int showOptions(String title, Object message, int optionType, int messageType, Object[] options, Component parentFrame, Icon icon) {
		return Popup.showOptions(title, message, optionType, messageType, options, parentFrame, icon, null);		
	}
	public static int showOptions(String title, Object message, int optionType, int messageType, Object[] options, Component parentFrame, Icon icon, Object initialValue) {
		return JOptionPane.showOptionDialog(parentFrame, message, title, optionType, messageType, icon, options, initialValue);		
	}
}
