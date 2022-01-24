import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Vehicle } from '../models/vehicle';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {
  private baseUrl = environment.baseUrl;
  private url = this.baseUrl +'api/vehicles';

  constructor(
    private auth: AuthService,
    private http: HttpClient,
    private date: DatePipe
  ) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: "Basic " + this.auth.getCredentials(),
        "X-Requested-With": "XMLHttpRequest"
      }
    }
    return options;
  }

  index(): Observable<Vehicle[]> {
    return this.http.get<Vehicle[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error(
            "VehiclesService.index(): error retrieving Vehicle list: " + err
          )
        );
      })
    );
  }

  show(id: number): Observable<Vehicle> {
    return this.http.get<Vehicle>(this.url + "/" + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error(
            "VehiclesService.show(): error retrieving Vehicle list: " + err
          )
        );
      })
    );
  }

  create(vehicle: Vehicle): Observable<Vehicle[]> {
    return this.http.post<Vehicle[]>(this.url, vehicle, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error(
            "VehiclesService.create(): error creating Vehicle list: " + err
          )
        );
      })
    );
  }

  update(vehicle: Vehicle) {
    return this.http.put<Vehicle>(this.url + "/" + vehicle.id, vehicle, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error("VehiclesService.destroy(): error updating Vehicle:");
        console.error(err);
        return throwError(
          () => new Error(
            "VehiclesService.update(): error updating Vehicle"
          )
        );
      })
    )
  }

  destroy(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error("VehiclesService.destroy(): error deleting Vehicle:");
        console.error(err);
        return throwError(
          () => new Error(
            "VehiclesService.destroy(): error deleting Vehicle"
          )
        );
      })
    );
  }

}
