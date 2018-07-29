import { ReportNearPage } from './../pages/report-near/report-near';
import { LoginPage } from './../pages/login/login';

import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';

import { MyApp } from './app.component';
import { UserProvider } from '../providers/user/user';
import { HttpClientModule } from '../../node_modules/@angular/common/http';
import { CarPage } from '../pages/car/car';
import { CarProvider } from '../providers/car/car';
import { ReportDriverPage } from '../pages/report-driver/report-driver';
import { LegislationsPage } from '../pages/legislations/legislations';
import { PaymentPage } from '../pages/payment/payment';
import { UsefullNumbersPage } from '../pages/usefull-numbers/usefull-numbers';
import { SettingsPage } from '../pages/settings/settings';
import { ReportProvider } from '../providers/report/report';
import { PaymentProvider } from '../providers/payment/payment';
import { StopProvider } from '../providers/stop/stop';
import { GoogleMapProvider } from '../providers/google-map/google-map';


@NgModule({
  declarations: [
    MyApp,
    LoginPage,
    CarPage,
    ReportDriverPage,
    LegislationsPage,
    PaymentPage,
    UsefullNumbersPage,
    SettingsPage,
    ReportNearPage
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    IonicModule.forRoot(MyApp,{
      scrollPadding: false,
      scrollAssist: false
    })
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    LoginPage,
    CarPage,
    ReportDriverPage,
    LegislationsPage,
    PaymentPage,
    UsefullNumbersPage,
    SettingsPage,
    ReportNearPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    UserProvider,
    CarProvider,
    ReportProvider,
    PaymentProvider,
    StopProvider,
    GoogleMapProvider,
    PaymentProvider
  ]
})
export class AppModule {}
