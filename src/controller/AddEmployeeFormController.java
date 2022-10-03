package controller;

import Badis.ServiceBa.G_Hotel;
import bo.BOFactory;
import bo.custom.CustomerBO;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AddEmployeeFormController implements Initializable {

    public JFXTextField txtcustID;
    public JFXTextField txtcustAddress;
    public JFXTextField txtcustName;
    public JFXTextField txtcity;
    public JFXComboBox<String> txtcustTital;
    public JFXComboBox<String> txtprovince;
    public JFXTextField txtEmail;
    public JFXTextField txtRegDate;
    public JFXTextField txtPhoneNo;
    public JFXButton btnDelete;
    public JFXButton btnSearch;
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public TableColumn<Object, Object> colCustID;
    public TableColumn<Object, Object> colCustTitle;
    public TableColumn<Object, Object> colCustName;
    public TableColumn<Object, Object> colCustPhone;
    public TableColumn<Object, Object> colCustAddress;
    public TableColumn<Object, Object> colCustEmail;
    public TableColumn<Object, Object> colCustcity;
    public TableColumn<Object, Object> colCustProvince;
    public TableColumn<Object, Object> colCustReg;
    public TableView<CustomerDTO> tblCustomer;
    CustomerBO customerBO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
        generateDateTime();
        setTxtcustID();
        colCustID.setCellValueFactory(new PropertyValueFactory<>("custID"));
        colCustTitle.setCellValueFactory(new PropertyValueFactory<>("custTital"));
        colCustName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        colCustPhone.setCellValueFactory(new PropertyValueFactory<>("custPhoneNo"));
        colCustAddress.setCellValueFactory(new PropertyValueFactory<>("custAddress"));
        colCustEmail.setCellValueFactory(new PropertyValueFactory<>("custEmail"));
        colCustcity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colCustProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colCustReg.setCellValueFactory(new PropertyValueFactory<>("RegDate"));
        loadAllCustomer();
    }

    private void loadAllCustomer() {
        try {
            ObservableList<CustomerDTO> allCustomers = customerBO.getAllCustomer();
            tblCustomer.setItems(allCustomers);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void generateDateTime() {
        this.txtRegDate.setText(LocalDate.now().toString());
    }

    public void setTxtcustID() {
        try {
            int id = this.customerBO.getRowCount();
            if (id == 0) {
                this.txtcustID.setText("C001");
            }

            if (id > 0 && id <= 8) {
                this.txtcustID.setText("C00" + (id + 1));
            }

            if (id >= 9 && id <= 98) {
                this.txtcustID.setText("C0" + (id + 1));
            }

            if (id >= 99) {
                this.txtcustID.setText("C" + (id + 1));
            }
        } catch (ClassNotFoundException | SQLException var2) {
            var2.printStackTrace();
        }
        //customer Count Code
    }


    public void CustomerAddOnAction() {
        try {
            boolean isAdded = customerBO.addCustomer(new CustomerDTO(
                    txtcustID.getText(),
                    txtcustTital.getValue(),
                    txtcustName.getText(),
                    txtPhoneNo.getText(),
                    txtcustAddress.getText(),
                    txtEmail.getText(),
                    txtcity.getText(),
                    txtprovince.getValue(),
                    txtRegDate.getText()));
            String tilte;
            String message;
            TrayNotification tray = new TrayNotification();

            if (isAdded) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Customer Added Successfully", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Added Successful";
                message = "Customer Is Added";

                loadAllCustomer();

            } else {
                (new Alert(Alert.AlertType.ERROR, "Customer Not Added ", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Added Un Successful";
                message = "Customer Is Not Added";

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            String tilte = "Customer Is Already On The Sever";
            String message = "Customer Is Not Added";

        }

    }

    public void customerDeleteOnAction() {
        String ID = txtcustID.getText();
        try {
            boolean isDelete = customerBO.deleteCustomer(ID);
            String tilte;
            String message;
            TrayNotification tray = new TrayNotification();

            if (isDelete) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Customer Delete Successfully", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Delete Success";
                message = "Customer Is Deleted";

                loadAllCustomer();
            } else {
                (new Alert(Alert.AlertType.ERROR, "Customer Not Delete", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Customer Not Found";
                message = "Sorry";

            }

        } catch (SQLException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public void searchCustomerOnAction() {
        try {
            String customerID = txtcustID.getText();
            CustomerDTO searchCustomer = customerBO.searchCustomer(customerID);
            if (searchCustomer != null) {
                txtcustID.setText(searchCustomer.getCustID());
                txtcustTital.setValue(searchCustomer.getCustTital());
                txtcustName.setText(searchCustomer.getCustName());
                txtPhoneNo.setText(searchCustomer.getCustPhoneNo());
                txtcustAddress.setText(searchCustomer.getCustAddress());
                txtEmail.setText(searchCustomer.getCustEmail());
                txtcity.setText(searchCustomer.getCity());
                txtprovince.setValue(searchCustomer.getProvince());
                txtRegDate.setText(searchCustomer.getRegDate());
                String tilte = "Customer Searched ";
                String message = "Customer Is " + "" + txtcustName.getText() + "";





            } else {
                String tilte = "Searched Customer Not Found";
                String message = "Try Again";

            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateOnAction() {

        try {
            String custID = txtcustID.getText();
            String custTital = txtcustTital.getValue();
            String custName = txtcustName.getText();
            String PhoneNo = txtPhoneNo.getText();
            String custAddress = txtcustAddress.getText();
            String Email = txtEmail.getText();
            String city = txtcity.getText();
            String province = txtprovince.getValue();
            String RegDate = txtRegDate.getText();
            CustomerDTO customerDTO = new CustomerDTO(custID, custTital, custName, PhoneNo, custAddress, Email, city, province, RegDate);
            boolean updateCustomer = customerBO.updateCustomer(customerDTO);
            String tilte;
            String message;
            TrayNotification tray = new TrayNotification();

            if (updateCustomer) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Customer Update Successfully", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Update Successful";
                message = "Customer Is Updated";


            } else {
                (new Alert(Alert.AlertType.ERROR, "Customer Not Update ", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Update Un Successful";
                message = "Customer Is Not Updated";


            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void tblMouseClick() {
        CustomerDTO c = tblCustomer.getSelectionModel().getSelectedItem();
        txtcustID.setText(c.getCustID());
        txtcustTital.setValue(c.getCustTital());
        txtcustName.setText(c.getCustName());
        txtPhoneNo.setText(c.getCustPhoneNo());
        txtcustAddress.setText(c.getCustAddress());
        txtEmail.setText(c.getCustEmail());
        txtcity.setText(c.getCity());
        txtprovince.setValue(c.getProvince());
        txtRegDate.setText(c.getRegDate());
    }

    @FXML
    void PDF(ActionEvent event) throws DocumentException {
        Document doc = new Document() ;
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Nom\\Desktop\\test1.pdf"));
            doc.open();

            com.itextpdf.text.Font f = new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN,25.0f  );


            Paragraph p = new Paragraph("M. /Mme (...) \n s'engage a respecter le reglement interieur de l'entreprise qui l'emploie. Le lieu de travail de M. /Mme (...) est situe a (lieu). \n Pour les dispositions ne figurant pas dans le contrat de travail, se referer a la convention collective applicable a l'entreprise :\n (preciser son intitule).",f);

            doc.add(p);

             doc.close();



            Desktop.getDesktop().open(new File("C:\\Users\\Nom\\Desktop\\test.pdf"));
        }catch (FileNotFoundException ex) {
            Logger.getLogger(G_Hotel.class.getName()).log(Level.SEVERE, null, ex);
        }catch (BadElementException ex) {
            Logger.getLogger(G_Hotel.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(G_Hotel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }




}
