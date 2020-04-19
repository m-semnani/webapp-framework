import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { AuthGaurdService } from './service/auth-guard.service';
import { UserAddComponent } from './user-add/user-add.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { UserListComponent } from './user-list/user-list.component';
import { UserUpdateComponent } from './user-update/user-update.component';

const routes: Routes = [
  { path: '', redirectTo: 'user', pathMatch: 'full' ,canActivate:[AuthGaurdService]},
  { path: 'users', component: UserListComponent ,canActivate:[AuthGaurdService]},
  { path: 'user-add', component: UserAddComponent ,canActivate:[AuthGaurdService]},
  { path: 'user-update/:id', component: UserUpdateComponent ,canActivate:[AuthGaurdService]},
  { path: 'user-details/:id', component: UserDetailsComponent ,canActivate:[AuthGaurdService]},
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent ,canActivate:[AuthGaurdService]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
