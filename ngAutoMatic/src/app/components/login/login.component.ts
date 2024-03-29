import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginUser: User = new User();

  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  login(user: User) {
    console.log("Loging user in");
    console.log(user);
    this.auth.login(user.email, user.password).subscribe({
      next: () => {
        this.router.navigateByUrl("/vehicles");
      },
      error: (err) => {
        console.error("LoginComponent.login(): error logging user in")
      }
    });
  }

}
