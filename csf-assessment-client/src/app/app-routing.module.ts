import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  // { path: '', component: HomeComponent },
  // { path: 'elephant', component: ElephantComponent },
  // { path: 'trex', component: TrexComponent },
  // { path: 'customer/:custId', component: CustomerComponent },
  // { path: '**', redirectTo: '/', pathMatch: 'full' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
