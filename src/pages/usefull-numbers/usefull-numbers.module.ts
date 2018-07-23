import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { UsefullNumbersPage } from './usefull-numbers';

@NgModule({
  declarations: [
    UsefullNumbersPage,
  ],
  imports: [
    IonicPageModule.forChild(UsefullNumbersPage),
  ],
})
export class UsefullNumbersPageModule {}
