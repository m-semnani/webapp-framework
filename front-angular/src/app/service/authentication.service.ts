import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';

export class User {
constructor(
    public status: string,
  ) { }

}

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(
    private httpClient: HttpClient
  ) {
  }

authenticate(username, password) {
      return this.httpClient.post<any>('http://localhost:8080/authenticate',{username,password}).pipe(
       map(
         userData => {
          sessionStorage.setItem('username',username);
          let tokenStr= 'Bearer '+ userData.token;
          sessionStorage.setItem('token', tokenStr);
          this.getUserDistilled(username, password)
            .subscribe(
              res => {
                  sessionStorage.setItem('isAdmin', res.admin);
              }
            );
          return userData;
         }
)

);
}

  isAdmin() {
    let res:boolean = (sessionStorage.getItem('isAdmin') === 'true');
    return res;
  }

  getUserDistilled(username, password) {
      return this.httpClient.post<any>('http://localhost:8080/userInfo',{username,password});
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('username')
    return !(user === null)
  }

  logOut() {
    sessionStorage.removeItem('username')
  }
}
