package com.gn.module.system;

import com.gn.global.GlobalConfig;
import com.gn.global.util.Alerts;
import com.gn.global.util.Utilities;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ChaosWong
 * @date 2020/1/1 22:08
 * @title
 */
public class recover implements Initializable {

	@FXML private TextField fileField= new TextField();
	private String fileAddress;

	@Override
	public void initialize ( URL location, ResourceBundle resources ) {
		fileField.textProperty().addListener( ( ov, old_val, new_val ) -> fileAddress=new_val );
	}

	@FXML
	public void submit(){
		try {
			Utilities.recover( 0,new File( GlobalConfig.BACK_UP_DIR + fileAddress ) );
			Alerts.success( "成功", "恢复成功" );

		}catch(Exception e){
			Alerts.error( "失败", e.getCause().toString() );

		}
	}

}
