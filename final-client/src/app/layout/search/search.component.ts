import { Component } from '@angular/core';
import {BehaviorSubject, filter, map, Observable, Subscription, switchMap} from "rxjs";
import {CarIndexData} from "../../models/car-index-data";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {CarService} from "../../services/car.service";
import {query} from "@angular/animations";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent {
  private subscription = new Subscription();
  private searchResultSub$
    = new BehaviorSubject<CarIndexData[]>([]);
  searchResult$: Observable<CarIndexData[]> = this.searchResultSub$.asObservable();

  searchForm: FormGroup = this.fb.group({
    query: new FormControl()
  });

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private carService: CarService) {
  }

  ngOnInit(): void {
    this.subscription.add(
      this.searchForm.valueChanges
        .pipe(
          filter(value => value?.query?.length >= 3),
          map(value => value?.query),
          switchMap(query => this.carService.carSearch(query))
        )
        .subscribe((res) => {
          this.searchResultSub$.next(res);
          console.log(res + "res");
        })
    );
  }

  navigateToProduct(data: CarIndexData): void {
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([`/cars/${data.carId}`]);});
  }

  ngOnDestroy(): void {
    this.searchResultSub$.complete();
    this.subscription.unsubscribe();
  }
}
