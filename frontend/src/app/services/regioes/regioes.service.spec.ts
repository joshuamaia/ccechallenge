import { TestBed } from '@angular/core/testing';

import { RegioesService } from './regioes.service';

describe('RegioesService', () => {
  let service: RegioesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegioesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
