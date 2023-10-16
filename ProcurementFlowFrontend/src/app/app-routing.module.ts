import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardMainComponent } from './admin-dashboard/admin-dashboard-main/admin-dashboard-main.component';
import { RegisterEmployeesComponent } from './admin-dashboard/Employees/register-employees/register-employees.component';
import { ViewEmployeesComponent } from './admin-dashboard/Employees/view-employees/view-employees.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  {path:'admin-dashboard',component:AdminDashboardMainComponent,children:[
    {path:'register-employees',component:RegisterEmployeesComponent},
    {path:'view-employees',component:ViewEmployeesComponent}
  ]},
  {path:'login',component:LoginComponent}
  
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
 
 }
