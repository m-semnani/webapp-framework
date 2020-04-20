import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { AuthGaurdService } from './service/auth-guard.service';

import { UserAddComponent } from './user-add/user-add.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { UserListComponent } from './user-list/user-list.component';
import { UserUpdateComponent } from './user-update/user-update.component';

import { ProductAddComponent } from './product-add/product-add.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductUpdateComponent } from './product-update/product-update.component';

const routes: Routes = [
  { path: '', redirectTo: 'user', pathMatch: 'full' ,canActivate:[AuthGaurdService]},
  { path: 'users', component: UserListComponent ,canActivate:[AuthGaurdService]},
  { path: 'user-add', component: UserAddComponent ,canActivate:[AuthGaurdService]},
  { path: 'user-update/:id', component: UserUpdateComponent ,canActivate:[AuthGaurdService]},
  { path: 'user-details/:id', component: UserDetailsComponent ,canActivate:[AuthGaurdService]},
  { path: 'products', component: ProductListComponent ,canActivate:[AuthGaurdService]},
  { path: 'product-add', component: ProductAddComponent ,canActivate:[AuthGaurdService]},
  { path: 'product-update/:id', component: ProductUpdateComponent ,canActivate:[AuthGaurdService]},
  { path: 'product-details/:id', component: ProductDetailsComponent ,canActivate:[AuthGaurdService]},
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent ,canActivate:[AuthGaurdService]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
