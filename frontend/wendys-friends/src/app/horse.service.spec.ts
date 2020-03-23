import { TestBed } from '@angular/core/testing';

import { HorseService } from './service/horse.service';

describe('HorseService', () => {
  let service: HorseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HorseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
