import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { LayoutModule } from '../../../layouts/layout.module';
import { AsideLeftDisplayDisabledComponent } from '../aside-left-display-disabled.component';
import { NgForm, FormsModule } from '@angular/forms';
import { PaymentsComponent } from './payments.component';

const routes: Routes = [
  {
      "path": "",
      "component": AsideLeftDisplayDisabledComponent,
      "children": [
          {
              "path": "",
              "component": PaymentsComponent
          }
      ]
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    LayoutModule,
    FormsModule
],
exports: [
    RouterModule
],
  declarations: [PaymentsComponent]
})
export class PaymentsModule { }
