import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ThemeComponent } from './theme/theme.component';
import { LayoutModule } from './theme/layouts/layout.module';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ScriptLoaderService } from "./_services/script-loader.service";
import { ThemeRoutingModule } from "./theme/theme-routing.module";
import { AuthModule } from "./auth/auth.module";
import { HomeDriverComponent } from './theme/pages/aside-left-display-disabled/home-driver/home-driver.component';
import { HomeOwnerComponent } from './theme/pages/aside-left-display-disabled/home-owner/home-owner.component';
import { FindCarplaceComponent } from './theme/pages/aside-left-display-disabled/find-carplace/find-carplace.component';
import { ExtensionStopsComponent } from './theme/pages/aside-left-display-disabled/extension-stops/extension-stops.component';
import { CarComponent } from './theme/pages/aside-left-display-disabled/car/car.component';
import { ReportDriverComponent } from './theme/pages/aside-left-display-disabled/report-driver/report-driver.component';
import { ReportNearComponent } from './theme/pages/aside-left-display-disabled/report-near/report-near.component';
import { PaymentComponent } from './theme/pages/aside-left-display-disabled/payment/payment.component';
import { UsefulNumbersComponent } from './theme/pages/aside-left-display-disabled/useful-numbers/useful-numbers.component';
import { LegislationsComponent } from './theme/pages/aside-left-display-disabled/legislations/legislations.component';
import { AddCarComponent } from './theme/pages/aside-left-display-disabled/addCar/addCar.component';
import { ReportHystoryComponent } from './theme/pages/aside-left-display-disabled/report-hystory/report-hystory.component';
import { ManagementParkComponent } from './theme/pages/aside-left-display-disabled/management-park/management-park.component';
import { ManagementSlotComponent } from './theme/pages/aside-left-display-disabled/management-slot/management-slot.component';
import { ReportOwnerComponent } from './theme/pages/aside-left-display-disabled/report-owner/report-owner.component';
import { SignupComponent } from './theme/pages/aside-left-display-disabled/signup/signup.component';

import { AgmCoreModule } from '@agm/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { GoogleMapService } from './_services/google-map.service';
import { PaymentService } from './_services/payment.service';

@NgModule({
    declarations: [
        ThemeComponent,
        AppComponent,
        HomeDriverComponent,
        HomeOwnerComponent,
        ExtensionStopsComponent,
        CarComponent,
        ReportDriverComponent,
        ReportNearComponent,
        PaymentComponent,
        UsefulNumbersComponent,
        LegislationsComponent,
        AddCarComponent,
        ReportHystoryComponent,
        ManagementParkComponent,
        ManagementSlotComponent,
        ReportOwnerComponent,
        SignupComponent
    ],
    imports: [
        LayoutModule,
        BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule,
        ThemeRoutingModule,
        AuthModule,
        AgmCoreModule.forRoot({
			apiKey: 'AIzaSyAUf_fIZF0iu40Uiwhj3RhFE3Kd1KrWUFw',
			libraries: ["places"]
		}),
        FormsModule,
        HttpClientModule
    ],
    providers: [
        ScriptLoaderService,
        GoogleMapService,
        PaymentService
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }