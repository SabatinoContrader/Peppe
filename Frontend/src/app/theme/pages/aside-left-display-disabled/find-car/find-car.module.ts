import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { LayoutModule } from '../../../layouts/layout.module';
import { AsideLeftDisplayDisabledComponent } from '../aside-left-display-disabled.component';
import { FindCarComponent } from './find-car.component';

const routes: Routes = [
  {
      "path": "",
      "component": AsideLeftDisplayDisabledComponent,
      "children": [
          {
              "path": "",
              "component": FindCarComponent
          }
      ]
  }
];

@NgModule({
  imports: [
    CommonModule, RouterModule.forChild(routes), LayoutModule
],
exports: [
    RouterModule
],
  declarations: [FindCarComponent]
})
export class FindCarModule { }
