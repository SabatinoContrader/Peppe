

import { LoginComponent } from "../components/login/login.component";
import { HomeDriverComponent } from "../components/home-driver/home-driver.component";
import { FormsModule } from "@angular/forms";
import { NgModule } from "@angular/core";
import { HttpClientModule } from "@angular/common/http";
import { AppRoutingModule } from "./app-routing.module";
import { UserService } from "../services/user.service";
import {BrowserModule} from '@angular/platform-browser';
import { AppComponent } from "./app.component";
import { HomeOwnerComponent } from "../components/home-owner/home-owner.component";
import { SignupComponent } from "../components/signup/signup.component";
import { ReportDriverComponent } from "../components/report-driver/report-driver.component";
import { ReportService } from "../services/report.service";
import { CarComponent } from "../components/car/car.component";
import { CarService } from "../services/car.service";
import { AddCarComponent } from "../components/addCar/addCar.component";
import { ReportHystoryComponent } from "../components/report-hystory/report-hystory.component";
import { ReportOwnerComponent } from "../components/report-owner/report-owner.component";
import { ReportNearComponent } from "../components/report-near/report-near.component";


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeDriverComponent,
    HomeOwnerComponent,
    SignupComponent,
    ReportDriverComponent,
    CarComponent,
    AddCarComponent,
    ReportHystoryComponent,
    ReportOwnerComponent,
    ReportNearComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [UserService, ReportService, CarService],
  bootstrap: [AppComponent]
})

export class AppModule { }
