

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


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeDriverComponent,
    HomeOwnerComponent,
    SignupComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})

export class AppModule { }
