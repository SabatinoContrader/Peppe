import { Component, ViewChild } from '@angular/core';
import { Nav, Platform } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { LoginPage } from '../pages/login/login';
import { HomeDriverPage } from '../pages/home-driver/home-driver';
import { CarPage } from '../pages/car/car';
import { ReportDriverPage } from '../pages/report-driver/report-driver';
import { PaymentPage } from '../pages/payment/payment';
import { UsefullNumbersPage } from '../pages/usefull-numbers/usefull-numbers';
import { LegislationsPage } from '../pages/legislations/legislations';
import { SettingsPage } from '../pages/settings/settings';


@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  @ViewChild(Nav) nav: Nav;

  rootPage: any = LoginPage;

  pages: Array<{title: string, component: any}>;

  constructor(public platform: Platform, public statusBar: StatusBar, public splashScreen: SplashScreen) {
    this.initializeApp();

    // used for an example of ngFor and navigation
    this.pages = [
      { title: 'Home', component: HomeDriverPage },
      { title: 'Gestione Auto', component: CarPage },
      { title: 'Segnalazioni', component: ReportDriverPage },
      { title: 'Pagamenti', component: PaymentPage },
      { title: 'Numeri Utili', component: UsefullNumbersPage },
      { title: 'Normative', component: LegislationsPage },
      { title: 'Impostazioni', component: SettingsPage },
      { title: 'Esci', component: LoginPage }
    ];

  }

  initializeApp() {
    this.platform.ready().then(() => {
      // Okay, so the platform is ready and our plugins are available.
      // Here you can do any higher level native things you might need.
      this.statusBar.styleDefault();
      this.splashScreen.hide();
    });
  }

  openPage(page) {
    // Reset the content nav to have just this page
    // we wouldn't want the back button to show in this scenario
    //this.nav.setRoot(page.component);

    this.nav.push(page.component);
  }
}


