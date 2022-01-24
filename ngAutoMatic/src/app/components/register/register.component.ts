import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(
    private auth: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  register(user: User) {
    console.log("Registering user");
    console.log(user);
    this.auth.register(user).subscribe({
      next: () => {
        this.auth.login(user.email, user.password).subscribe({
          next: () => {
            this.router.navigateByUrl("/vehicles");
          },
          error: (err) => {
            console.error("Error loging in");
            console.error(err);
          }
        })
      },
      error: (err) => {
        console.error("RegisterComponent.register(): error registering account");
        console.error(err);
      }
    })
  }

}
