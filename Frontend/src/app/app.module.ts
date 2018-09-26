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

import { AgmCoreModule } from '@agm/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { GoogleMapService } from './_services/google-map.service';
import { PaymentService } from './_services/payment.service';
import { StopService } from './_services/stop.service';
import { AclService } from './_services/acl.service';
import { CarService } from './_services/car.service';
import { ClipboardService } from './_services/clipboard.sevice';
import { DataTableService } from './_services/datatable.service';
import { GlobalErrorHandler } from './_services/error-handler.service';
import { HttpUtilsService } from './_services/http-utils.service';
import { LogsService } from './_services/logs.service';
import { MessengerService } from './_services/messenger.service';
import { QuickSearchService } from './_services/quick-search.service';
import { ReportService } from './_services/report.service';
import { SlotService } from './_services/slot.service';
import { SplashScreenService } from './_services/splash-screen.service';
import { TranslationService } from './_services/translation.service';
import { UserService } from './_services/user.service';
import { UtilsService } from './_services/utils.service';

@NgModule({
    declarations: [
        ThemeComponent,
        AppComponent
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
        // Maybe move the ones below to each required module?
        GoogleMapService,
        PaymentService,
        StopService,
        AclService,
        CarService,
        ClipboardService,
        DataTableService,
        GlobalErrorHandler,
        HttpUtilsService,
        LogsService,
        MessengerService,
        QuickSearchService,
        ReportService,
        SlotService,
        SplashScreenService,
        TranslationService,
        UserService,
        UtilsService
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }