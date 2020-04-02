import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Horse } from 'src/app/dto/horse';
import { SearchHorse } from 'src/app/dto/searchHorse';

@Component({
  selector: 'app-horse-search',
  templateUrl: './horse-search.component.html',
  styleUrls: ['./horse-search.component.scss']
})
export class HorseSearchComponent implements OnInit {
  @Output() searchHorse: EventEmitter<SearchHorse> = new EventEmitter();

  name: string;
  notes: string;
  rating: number;
  birthday: string;
  race: string;

  constructor() { }

  ngOnInit(): void {
  }

  onSubmit() {
    if (this.rating === undefined)
      this.rating = 0;

    const horse = new SearchHorse(this.name, this.notes, this.rating, this.birthday, this.race);

    this.searchHorse.emit(horse);
  }
}
