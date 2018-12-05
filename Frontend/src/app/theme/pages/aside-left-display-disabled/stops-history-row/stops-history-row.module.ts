import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StopsHistoryRowComponent } from './stops-history-row.component';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { AsideLeftDisplayDisabledComponent } from '../aside-left-display-disabled.component';
import { LayoutModule } from '../../../layouts/layout.module';

const routes: Routes = [
  {
      "path": "",
      "component": AsideLeftDisplayDisabledComponent,
      
  }
];

@NgModule({
  imports: [
    CommonModule, RouterModule.forChild(routes), LayoutModule, FormsModule
],
exports: [
    RouterModule
],
  declarations: []
})
export class StopsHistoryRowModule { }
