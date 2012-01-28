package frontend;

import backend.ServicesProvider;
import java.lang.Exception;
import javafx.ext.swing.SwingButton;
import javafx.ext.swing.SwingHorizontalAlignment;
import javafx.ext.swing.SwingIcon;
import javafx.ext.swing.SwingLabel;
import javafx.ext.swing.SwingList;
import javafx.ext.swing.SwingListItem;
import javafx.ext.swing.SwingTextField;
import javafx.ext.swing.SwingVerticalAlignment;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringLocalizer;
import org.jivesoftware.smack.RosterEntry;
import javafx.scene.Cursor;

/**
 * UI definition script
 *
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 */

Stage {
    //For the labels and messages localization see links below :
    //http://java.sun.com/javafx/1/docs/api/javafx.util/javafx.util.StringLocalizer.html
    //http://blogs.sun.com/naotoj/entry/easier_localization_in_javafx_script

    /*Define components used*/
    var loginTextBox = SwingTextField {
        columns: 20
        text: ""
        editable: true
    };
    var loginLabel = SwingLabel {
        text:
        StringLocalizer{
            key:"loginLabel"}.localizedString
    };
    var passwordTextBox = SwingTextField {
        columns: 10
        text: ""
        editable: true
    };
    var passwordLabel = SwingLabel {
        text:
        StringLocalizer{
            key:"passwordLabel"}.localizedString
    };
    var buddiesListLabel = SwingLabel {
        text:
        StringLocalizer{
            key:"buddiesListLabel"}.localizedString
    };
    var contacts : SwingListItem[];
    var contactsList = SwingList{
        items: bind contacts //Bind the item objects container to the list items collection
        width:600
    };
    var infoLabel = SwingLabel {
        id:"infoLabel"
        text:"..."
        width:600
        horizontalAlignment: SwingHorizontalAlignment.CENTER;
        verticalAlignment: SwingVerticalAlignment.CENTER;
    };
    var actionButtonIcon = SwingIcon{
        image:Image {
            url: "{__DIR__}images/getBuddies.png"
            backgroundLoading: false
        };
    };
    var actionButton = SwingButton {
        text:
        StringLocalizer{
            key:"actionButton"}.localizedString
        icon:actionButtonIcon
        action:function() {
            try{
                //Update scene cursor
                Stage.stages[0].scene.cursor = Cursor.WAIT;
                //Call service
                var buddies : RosterEntry[] = ServicesProvider.obtainsBuddies(loginTextBox.text, passwordTextBox.text);
                //Update list
                //---Delete all elements from the sequence (array) but not the sequence itself.
                delete contacts;
                //---Fill the sequence with the content returned by the service
                for(buddy in buddies){
                insert SwingListItem{
                        selected:false;
                        text:'{buddy.getUser()} ({buddy.getName()})';
                        value:buddy.getName();
                } into contacts;
                }
                infoLabel.text = ' {sizeof(buddies)} {StringLocalizer{key:"infoLabelOK"}.localizedString}';
                infoLabel.foreground = Color.GREEN;
            } catch (e: Exception){
                infoLabel.text = '{StringLocalizer{key:"infoLabelKO"}.localizedString} {e.getMessage()}';
                infoLabel.foreground = Color.RED;
            }finally {
                //Update scene cursor
                Stage.stages[0].scene.cursor = Cursor.DEFAULT;
            }
        }
}

    /*Define UI*/
    title:
    StringLocalizer{
        key:"mainTitle"}.localizedString
    width: 630
    height: 280
    scene: Scene {
        stylesheets: ["{__DIR__}style/style.css"]
        content: VBox{
            spacing:5
            content:[HBox{
                    spacing: 5
                    content:[loginLabel,loginTextBox,passwordLabel,passwordTextBox]
                },buddiesListLabel,contactsList,infoLabel,actionButton]
        }

    };//End of Scene
    style:StageStyle.TRANSPARENT
}//End of Stage