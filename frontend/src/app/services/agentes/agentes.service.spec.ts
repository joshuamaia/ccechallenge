import { TestBed } from '@angular/core/testing';

import { AgentesService } from './agentes.service';

describe('AgentesService', () => {
  let service: AgentesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AgentesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
