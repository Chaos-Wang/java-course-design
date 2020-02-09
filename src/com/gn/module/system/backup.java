package com.gn.module.system;

import com.gn.global.util.Alerts;
import com.gn.global.util.Utilities;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ChaosWong
 * @date 2020/1/1 21:09
 * @title
 */
public class backup implements Initializable {
	@FXML private CheckBox checkBox1 = new CheckBox();
	@FXML private CheckBox checkBox2 = new CheckBox();
	@FXML private CheckBox checkBox3 = new CheckBox();
	@FXML private CheckBox checkBox4 = new CheckBox();
	@FXML private CheckBox checkBox5 = new CheckBox();

	private boolean[] boolBackUp = new boolean[5];

	@Override
	public void initialize( URL location, ResourceBundle resources) {
			initCb();
	}
	public void initCb(){
		checkBox1.setSelected( false );
		checkBox1.selectedProperty().addListener( ( ov, old_val, new_val ) -> boolBackUp[ 0 ]=new_val );

		checkBox2.setSelected( false );
		checkBox2.selectedProperty().addListener( ( ov, old_val, new_val ) -> boolBackUp[ 1 ]=new_val );

		checkBox3.setSelected( false );
		checkBox3.selectedProperty().addListener( ( ov, old_val, new_val ) -> boolBackUp[ 2 ]=new_val );

		checkBox4.setSelected( false );
		checkBox4.selectedProperty().addListener( ( ov, old_val, new_val ) -> boolBackUp[ 3 ]=new_val );

		checkBox5.setSelected( false );
		checkBox5.selectedProperty().addListener( ( ov, old_val, new_val ) -> boolBackUp[ 4 ]=new_val );
	}

	@FXML
	public void submit(){
		try{
			Utilities.backup( boolBackUp );
			Alerts.success( "成功", "备份成功" );

		}catch(Exception e){
			Alerts.error( "失败", e.getCause().toString() );
		}
	}
}
