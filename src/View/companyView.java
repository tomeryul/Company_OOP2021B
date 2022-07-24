package View;

import java.util.Vector;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import Liseners.CompanyListrnerToUi;
import Model.Department;
import Model.Employee;
import Model.EmployeeBaseSalary;
import Model.EmployeeHourly;
import Model.EmployeeSalesMan;
import Model.Role;
import Model.workingHours.epreferenceOfWorkHours;


public class companyView implements AbstractCompanyView {
	protected static final String ActionEvent = null;
	private Vector<CompanyListrnerToUi> allListeners = new Vector<CompanyListrnerToUi>();

	private boolean IsSyncRole;
	private boolean IsSyncDepartment;
	private String workingType;

	public companyView(Stage theStage) {
		
		allListeners = new Vector<CompanyListrnerToUi>();

		theStage.setTitle("Company - By Tomer and Ohad");
		BorderPane bPMain = new BorderPane();

		// add company Name
		
		String companyName = JOptionPane.showInputDialog(null, "Add company name :");
		
		// Top
		StackPane SpTitle = new StackPane();
		Label lblTitle = new Label("Company : " + companyName);
		lblTitle.setFont(new Font(30));
		lblTitle.setEffect(new DropShadow(30, Color.BLACK));
		lblTitle.setTextFill(Color.BLACK);
		SpTitle.getChildren().addAll(lblTitle);
		lblTitle.setPadding(new Insets(25));

		VBox vbMain = new VBox();

		// add Employee,role,department
		Button btnCreate = new Button("Add");
		vbMain.setSpacing(20);
		
		btnCreate.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				theStage.close();
				Stage stageCreate = new Stage();
				stageCreate.setTitle("Add Employee/Role/Department ");
				VBox vbCreate = new VBox();
				vbCreate.setPadding(new Insets(20));

				Label lbAddEmployee = new Label("Choose Entity to add :");

				ToggleGroup tgCreate = new ToggleGroup();

				RadioButton rbAddEmployee = new RadioButton("Add Employee");
				rbAddEmployee.setToggleGroup(tgCreate);
				rbAddEmployee.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(javafx.event.ActionEvent event) {

						stageCreate.close();
						// Add Employee
						Stage stageAddEmployee = new Stage();

						VBox vbAddEmployee = new VBox();
						Label lbAddEmployee = new Label("Add Employee :");

						Label lbNameOfEmployee = new Label("Add Employee name :");
						TextField tFNameOfEmployee = new TextField();

						Label lbPrefStratWorking = new Label("Choose preferred hour to Strat youre Working day(the ending will be 9 hours later):");
						TextField tFPrefStratWorking = new TextField();
						
						Label lbEmployeeType = new Label("Choose Employee Type : ");

						ToggleGroup tfEmployeeType = new ToggleGroup();

						RadioButton rbBaseSalary = new RadioButton("Base Salary");
						rbBaseSalary.setToggleGroup(tfEmployeeType);

						RadioButton rbHourly = new RadioButton("Hourly");
						rbHourly.setToggleGroup(tfEmployeeType);

						RadioButton rbSalesMan = new RadioButton("Sales Man");
						rbSalesMan.setToggleGroup(tfEmployeeType);

						Label lbChooseRole = new Label("Choose role : ");
						ComboBox<String> cBAllRoles = new ComboBox<String>();
						for (CompanyListrnerToUi l : allListeners) {

							for (int i = 0; i < l.getAllRolesToUi().size(); i++) {

								cBAllRoles.getItems().add(l.getNameOfRoleToUi(i));

							}
						}
						
						Label lbChooseDepartment = new Label("Choose Department : ");
						ComboBox<String> cBAllDepartments = new ComboBox<String>();
						for (CompanyListrnerToUi l : allListeners) {

							for (int i = 0; i < l.getAllDepartmentsToUi().size(); i++) {

								cBAllDepartments.getItems().add(l.getNameOfDepartmentToUi(i));
							}
						}

						Button btnSubmit = new Button();
						btnSubmit.setText("Submit");
						btnSubmit.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(javafx.event.ActionEvent event) {
								for (CompanyListrnerToUi l : allListeners) {

									if (!tFNameOfEmployee.getText().isEmpty()) {
										if (!tFPrefStratWorking.getText().isEmpty()
												&& isNumeric(tFPrefStratWorking.getText())) {
											if (TextFieldToInt(tFPrefStratWorking)<=24&&
													TextFieldToInt(tFPrefStratWorking)>=0){ 
												if (!cBAllRoles.getSelectionModel().isEmpty()) {
													if (!cBAllDepartments.getSelectionModel().isEmpty()) {

														if (rbBaseSalary.isSelected()) {
															EmployeeBaseSalary employee = new EmployeeBaseSalary(
																	tFNameOfEmployee.getText(),
																	TextFieldToInt(tFPrefStratWorking),
																	(TextFieldToInt(tFPrefStratWorking)+9),
																	cBAllRoles.getValue());
															l.addEmployeeToUi(employee, cBAllDepartments.getValue());
															stageAddEmployee.close();
															theStage.show();
														} else if (rbHourly.isSelected()) {
															EmployeeHourly employee = new EmployeeHourly(
																	tFNameOfEmployee.getText(),
																	TextFieldToInt(tFPrefStratWorking),
																	(TextFieldToInt(tFPrefStratWorking)+9),
																	cBAllRoles.getValue());
															l.addEmployeeToUi(employee, cBAllDepartments.getValue());
															stageAddEmployee.close();
															theStage.show();
														} else if (rbSalesMan.isSelected()) {
															EmployeeSalesMan employee = new EmployeeSalesMan(
																	tFNameOfEmployee.getText(),
																	TextFieldToInt(tFPrefStratWorking),
																	(TextFieldToInt(tFPrefStratWorking)+9),
																	cBAllRoles.getValue());
															l.addEmployeeToUi(employee, cBAllDepartments.getValue());
															stageAddEmployee.close();
															theStage.show();
														}

													}
												}
											}
										}
									}
								}

							}
						});

						vbAddEmployee.setPadding(new Insets(20));
						vbAddEmployee.getChildren().addAll(lbAddEmployee, lbNameOfEmployee, tFNameOfEmployee,
								lbPrefStratWorking, tFPrefStratWorking , 
								lbEmployeeType, rbBaseSalary, rbHourly, rbSalesMan, lbChooseRole, cBAllRoles,
								lbChooseDepartment, cBAllDepartments, btnSubmit);

						stageAddEmployee.setScene(new Scene(vbAddEmployee, 500, 450));
						stageAddEmployee.show();
					}
				});

				RadioButton rbAddRole = new RadioButton("Add Role");
				rbAddRole.setToggleGroup(tgCreate);
				rbAddRole.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(javafx.event.ActionEvent event) {
						stageCreate.close();

						// AddRole

						Stage stageAddRole = new Stage();

						VBox vbAddRole = new VBox();
						vbAddRole.setPadding(new Insets(20));
						Label lbAddRole = new Label("Add Role");

						Label lbNameOfRole = new Label("Add Role name :");
						TextField tFNameOfRole = new TextField();

						Label lbRoleIsSync = new Label("Role Is Sync ? ");

						ToggleGroup tgRoleIsSync = new ToggleGroup();

						RadioButton rbAddRoleIsSyncT = new RadioButton("True");
						rbAddRoleIsSyncT.setToggleGroup(tgRoleIsSync);
						rbAddRoleIsSyncT.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(javafx.event.ActionEvent event) {
								IsSyncRole = true;
							}
						});
						RadioButton rbAddRoleIsSyncF = new RadioButton("False");
						rbAddRoleIsSyncF.setToggleGroup(tgRoleIsSync);
						rbAddRoleIsSyncF.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(javafx.event.ActionEvent event) {
								IsSyncRole = false;
							}
						});
						Button btnSubmit = new Button();
						btnSubmit.setText("Submit");
						btnSubmit.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(javafx.event.ActionEvent event) {

								if (!tFNameOfRole.getText().isEmpty()) {
									if (rbAddRoleIsSyncT.isSelected() || rbAddRoleIsSyncF.isSelected()) {
										Role role = new Role(tFNameOfRole.getText(), IsSyncRole);
										for (CompanyListrnerToUi l : allListeners) {
											l.addRoleToUi(role);
										}
										stageAddRole.close();
										theStage.show();
									}
								}
							}
						});

						vbAddRole.setPadding(new Insets(20));
						vbAddRole.getChildren().addAll(lbAddRole, lbNameOfRole, tFNameOfRole, lbRoleIsSync,
								rbAddRoleIsSyncT, rbAddRoleIsSyncF, btnSubmit);

						stageAddRole.setScene(new Scene(vbAddRole, 500, 450));
						stageAddRole.show();
					}
				});

				RadioButton rbAddDepartment = new RadioButton("Add Department");
				rbAddDepartment.setToggleGroup(tgCreate);

				rbAddDepartment.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(javafx.event.ActionEvent event) {
						stageCreate.close();

						// AddDepartment

						Stage stageAddDepartment = new Stage();

						VBox vbAddDepartment = new VBox();
						Label lbAddDepartment = new Label("Add Department");

						Label lbNameOfDepartment = new Label("Add Department name :");
						TextField tFNameOfDepartment = new TextField();

						Label lbDepartmentIsSync = new Label("Department Is Sync ? ");

						ToggleGroup tgDepartmentIsSync = new ToggleGroup();

						RadioButton rbAddDepartmentIsSyncT = new RadioButton("True");
						rbAddDepartmentIsSyncT.setToggleGroup(tgDepartmentIsSync);
						rbAddDepartmentIsSyncT.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(javafx.event.ActionEvent event) {
								IsSyncDepartment = true;
							}
						});

						RadioButton rbAddDepartmentIsSyncF = new RadioButton("False");
						rbAddDepartmentIsSyncF.setToggleGroup(tgDepartmentIsSync);
						rbAddDepartmentIsSyncF.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(javafx.event.ActionEvent event) {
								IsSyncDepartment = true;
							}
						});

						Button btnSubmit = new Button();
						btnSubmit.setText("Submit");
						btnSubmit.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(javafx.event.ActionEvent event) {

								if (!tFNameOfDepartment.getText().isEmpty()) {
									if (rbAddDepartmentIsSyncT.isSelected() || rbAddDepartmentIsSyncF.isSelected()) {
										Department department = new Department(tFNameOfDepartment.getText(),
												IsSyncDepartment);
										for (CompanyListrnerToUi l : allListeners) {
											l.addDepartmentToUi(department);
										}
										stageAddDepartment.close();
										theStage.show();
									}
								}
							}
						});

						vbAddDepartment.setPadding(new Insets(20));
						vbAddDepartment.getChildren().addAll(lbAddDepartment, lbNameOfDepartment, tFNameOfDepartment,
								lbDepartmentIsSync, rbAddDepartmentIsSyncT, rbAddDepartmentIsSyncF, btnSubmit);

						stageAddDepartment.setScene(new Scene(vbAddDepartment, 500, 450));
						stageAddDepartment.show();
					}
				});

				vbCreate.getChildren().addAll(lbAddEmployee, rbAddEmployee, rbAddRole, rbAddDepartment);

				stageCreate.setScene(new Scene(vbCreate, 500, 450));
				stageCreate.show();
			}
		});

		// show all details
		Button btnShowAllDet = new Button("Show all details ");
		btnShowAllDet.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(javafx.event.ActionEvent event) {
				for (CompanyListrnerToUi l : allListeners) {
					JOptionPane.showMessageDialog(null, l.toStringToUi());
				}
			}
		});
		// Change Role hours
		Button btnChangeRoleHours = new Button("Change Role hours ");
		btnChangeRoleHours.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				theStage.close();
				Stage stageChangeRoleHours = new Stage();
				stageChangeRoleHours.setTitle(" Change Role hours ");
				VBox vbChangeRoleHours = new VBox();

				Label lbWorkingType = new Label("Choose Working Type :");

				ToggleGroup tgWorkingType = new ToggleGroup();

				RadioButton RbEARLIER = new RadioButton("EARLIER");
				RbEARLIER.setToggleGroup(tgWorkingType);
				RbEARLIER.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(javafx.event.ActionEvent event) {
						workingType = "EARLIER";

					}
				});
				RadioButton RbLATER = new RadioButton("LATER");
				RbLATER.setToggleGroup(tgWorkingType);
				RbLATER.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(javafx.event.ActionEvent event) {
						workingType = "LATER";

					}
				});
				RadioButton RbDEFAULT = new RadioButton("DEFAULT");
				RbDEFAULT.setToggleGroup(tgWorkingType);
				RbDEFAULT.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(javafx.event.ActionEvent event) {
						workingType = "DEFAULT";

					}
				});
				RadioButton RbFREE = new RadioButton("FREE");
				RbFREE.setToggleGroup(tgWorkingType);
				RbFREE.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(javafx.event.ActionEvent event) {
						workingType = "FREE";

					}
				});
				Label lbChooseRoleName = new Label("Choose Role :");
				ComboBox<String> cmbChooseRoleName = new ComboBox<String>();
				for (CompanyListrnerToUi l : allListeners) {
					for (int i = 0; i < l.getAllRolesToUi().size(); i++) {
						cmbChooseRoleName.getItems().add(l.getAllRolesToUi().get(i).getNameOfRole());
					}
				}

				Label lbHourToChange = new Label("Hour to change :");
				TextField tFHourToChange = new TextField();

				Button submit = new Button();
				submit.setText("Submit");
				submit.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(javafx.event.ActionEvent event) {
						for (CompanyListrnerToUi l : allListeners) {
							if (RbEARLIER.isSelected() || RbLATER.isSelected() || RbDEFAULT.isSelected()
									|| RbFREE.isSelected()) {
								if (l.isPreferenceToUi(workingType)) {
									if (TextFieldToInt(tFHourToChange)<=24&&
											TextFieldToInt(tFHourToChange)>=0){
										if (!cmbChooseRoleName.getSelectionModel().isEmpty()) {
											if (!tFHourToChange.getText().isEmpty()
													&& isNumeric(tFHourToChange.getText())) {

												l.changeRoleHoursToUi(l.getePrefToUi(workingType),
														TextFieldToInt(tFHourToChange), cmbChooseRoleName.getValue());
												stageChangeRoleHours.close();
												theStage.show();
											}
										}
									}
								}
							}
						}
					}
				});
				vbChangeRoleHours.setPadding(new Insets(20));
				vbChangeRoleHours.getChildren().addAll(lbWorkingType, RbEARLIER, RbLATER, RbDEFAULT, RbFREE,
						lbChooseRoleName, cmbChooseRoleName, lbHourToChange, tFHourToChange, submit);

				stageChangeRoleHours.setScene(new Scene(vbChangeRoleHours, 500, 450));
				stageChangeRoleHours.show();

			}

		});

		// Change Deppartment Hours
		Button btnChangeDeppartmentHours = new Button("Change Deppartment Hours ");
		btnChangeDeppartmentHours.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				theStage.close();
				Stage stageChangeDeppartmentHours = new Stage();
				stageChangeDeppartmentHours.setTitle(" Change Deppartment hours ");
				VBox vbChangeDeppartmentHours = new VBox();
				
				Label lbWorkingTYpe= new Label("choose Working type :");

				ToggleGroup tgWorkingType = new ToggleGroup();

				RadioButton RbEARLIER = new RadioButton("EARLIER");
				RbEARLIER.setToggleGroup(tgWorkingType);
				RbEARLIER.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(javafx.event.ActionEvent event) {
						workingType = "EARLIER";

					}
				});
				RadioButton RbLATER = new RadioButton("LATER");
				RbLATER.setToggleGroup(tgWorkingType);
				RbLATER.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(javafx.event.ActionEvent event) {
						workingType = "LATER";

					}
				});
				RadioButton RbDEFAULT = new RadioButton("DEFAULT");
				RbDEFAULT.setToggleGroup(tgWorkingType);
				RbDEFAULT.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(javafx.event.ActionEvent event) {
						workingType = "DEFAULT";

					}
				});
				RadioButton RbFREE = new RadioButton("FREE");
				RbFREE.setToggleGroup(tgWorkingType);
				RbFREE.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(javafx.event.ActionEvent event) {
						workingType = "FREE";

					}
				});
				
				Label lbChooseDeppartmentName=new Label("Choose Deppartment Name");
				ComboBox<String> cmbChooseDeppartmentName=new ComboBox<String>();
				for (CompanyListrnerToUi l : allListeners) {
					for (int i = 0; i <l.getAllDepartmentsToUi().size(); i++) {
						cmbChooseDeppartmentName.getItems().add(l.getAllDepartmentsToUi().get(i).getNameOfDepartment());
					}
				}
				Label lbHourToChange = new Label("Hour to change :");
				TextField tFHourToChange = new TextField();
				
				Button submit = new Button();
				submit.setText("Submit");
				submit.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(javafx.event.ActionEvent event) {
						for (CompanyListrnerToUi l : allListeners) {
							if (RbEARLIER.isSelected() || RbLATER.isSelected() || RbDEFAULT.isSelected()
									|| RbFREE.isSelected()) {
								if (TextFieldToInt(tFHourToChange)<=24&&
										TextFieldToInt(tFHourToChange)>=0){
									if (l.isPreferenceToUi(workingType)) {
										if (!cmbChooseDeppartmentName.getSelectionModel().isEmpty()) {
											if (!tFHourToChange.getText().isEmpty()
													&& isNumeric(tFHourToChange.getText())) {

												l.changeDepartmentHoursToUi(l.getePrefToUi(workingType),
														TextFieldToInt(tFHourToChange), cmbChooseDeppartmentName.getValue());
												stageChangeDeppartmentHours.close();
												theStage.show();
											}
										}
									}
								}
							}
						}
					}
				});
				
				vbChangeDeppartmentHours.setPadding((new Insets(20)));
				vbChangeDeppartmentHours.getChildren().addAll(lbWorkingTYpe,RbEARLIER,RbLATER,RbDEFAULT,RbFREE,lbChooseDeppartmentName,
						cmbChooseDeppartmentName,lbHourToChange,tFHourToChange,submit);
				
				stageChangeDeppartmentHours.setScene(new Scene(vbChangeDeppartmentHours, 500, 450));
				stageChangeDeppartmentHours.show();
			}

		});

		// Show Profit
		Button btnShowProfit = new Button("Show Profit ");
		btnShowProfit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(javafx.event.ActionEvent event) {
				for (CompanyListrnerToUi l : allListeners) {
					JOptionPane.showMessageDialog(null, l.showAllProfitsToUi());
				}
			}
		});

		// EXIT
		Button btnExit = new Button("EXIT");
		btnExit.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Stage stage = (Stage) btnExit.getScene().getWindow();
				//for (CompanyListrnerToUi l : allListeners) {			
				//}
				stage.close();
			}

		});

		vbMain.setPadding(new Insets(20));
		vbMain.getChildren().addAll(btnCreate, btnShowAllDet, btnChangeRoleHours, btnChangeDeppartmentHours,
				btnShowProfit, btnExit);

		bPMain.setLeft(vbMain);
		bPMain.setTop(SpTitle);
		theStage.setScene(new Scene(bPMain, 500, 450));
		theStage.show();
	}

	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private int TextFieldToInt(TextField TF) {

		Integer value = Integer.valueOf(TF.getText());

		return value;
	}

	@Override
	public void registerListener(CompanyListrnerToUi listener) {
		allListeners.add(listener);
	}

	@Override
	public void calculateProfitToUi() {

	}

	@Override
	public double getProfitToUi() {
		return 0;
	}

	@Override
	public String toStringToUi() {
		return null;
	}

	@Override
	public String showAllProfitsToUi() {
		return null;
	}

	@Override
	public String showAllRolesToUi() {
		return null;
	}

	@Override
	public boolean isRoleAlreadyToUi(String roleName) {
		return false;
	}

	@Override
	public boolean isDepartmentAlreadyToUi(String departmentName) {
		return false;
	}

	@Override
	public boolean isPreferenceToUi(String preference) {
		return false;
	}

	@Override
	public String showAllPreferencesToUi() {
		return null;
	}

	@Override
	public void changeDepartmentHoursToUi(epreferenceOfWorkHours pref, int numOfHours, String departmentName) {

	}

	@Override
	public void updateRolesProfitsToUi() {

	}

	@Override
	public String showAllDepartmentsToUi() {
		return null;
	}

	@Override
	public boolean addEmployeeToUi(Employee employee, String departmentName) {
		return false;
	}

	@Override
	public boolean isEmployeeExistToUi(Employee employee) {
		return false;
	}

	@Override
	public void addDepartmentToUi(Department department) {

	}

	@Override
	public void addRoleToUi(Role role) {
	}

	@Override
	public boolean alreadyExitRoleToUi(Role role) {
		return false;
	}

	@Override
	public boolean changeRoleHoursToUi(epreferenceOfWorkHours pref, int numOfHours, String roleName) {
		return false;
	}

	@Override
	public Role getRoleBynameToUi(String roleName) {
		return null;
	}

	@Override
	public Vector<Department> getAllDepartmentsToUi() {

		return null;
	}

	@Override
	public Vector<Role> getAllRolesToUi() {
		return null;
	}

	@Override
	public Vector<Employee> getAllEmployeesToUi() {
		return null;
	}

	@Override
	public String getNameOfRoleToUi(int index) {
		return null;
	}

	@Override
	public String getNameOfDepartmentToUi(int index) {
		return null;
	}

	@Override
	public epreferenceOfWorkHours getePrefToUi(String pref) {
		return null;
	}
	
}
