import { Component, OnInit } from '@angular/core';
import { AgentesService } from 'src/app/services/agentes/agentes.service';
import { MatTableDataSource } from '@angular/material/table';
import swal from 'sweetalert2';
import { RegioesService } from 'src/app/services/regioes/regioes.service';
import { IRegiao } from 'src/app/model/RegiaoModel';

@Component({
  selector: 'app-agentes',
  templateUrl: './agentes.component.html',
  styleUrls: ['./agentes.component.css'],
})
export class AgentesComponent implements OnInit {
  arquivosSelecionados: File[];
  dataSource: any;
  displayedColumns = ['Nome', 'Tamanho'];
  enviandoArquivo: boolean = false;
  regioes: IRegiao[];

  constructor(private agentesService: AgentesService) {
    this.arquivosSelecionados = [];
    this.regioes = [];
  }

  ngOnInit(): void {}

  //Recebe a seleção de arquivos e salva na variável 'arquivosSelecionados'
  selecaoArquivos(event: any) {
    this.arquivosSelecionados = [].slice.call(event.target.files);
    this.dataSource = new MatTableDataSource(this.arquivosSelecionados);
  }

  formatSizeUnits(bytes: any) {
    if (bytes >= 1073741824) {
      bytes = (bytes / 1073741824).toFixed(2) + ' GB';
    } else if (bytes >= 1048576) {
      bytes = (bytes / 1048576).toFixed(2) + ' MB';
    } else if (bytes >= 1024) {
      bytes = (bytes / 1024).toFixed(2) + ' KB';
    } else if (bytes > 1) {
      bytes = bytes + ' bytes';
    } else if (bytes == 1) {
      bytes = bytes + ' byte';
    } else {
      bytes = '0 bytes';
    }
    return bytes;
  }

  uploadArquivos() {
    if (this.arquivosSelecionados.length != 0) {
      this.enviandoArquivo = true;
      let enviado = false;
      let quantidadeDeEnvios = 0;
      let quantidadeDeArquivos = this.arquivosSelecionados.length;
      let arquivosSucesso = 'Arquivos enviados com sucesso: ';
      let arquivosErro = 'Arquivos enviados com erro: ';
      console.log(this.arquivosSelecionados);
      this.arquivosSelecionados.forEach((file) => {
        this.agentesService.cadastrar(file).subscribe({
          next: (x) => {
            quantidadeDeEnvios++;
            enviado = quantidadeDeEnvios === quantidadeDeArquivos;
            this.dataSource = new MatTableDataSource([]);
            arquivosSucesso = arquivosSucesso + ' - ' + file.name;
            if (enviado) {
              this.enviandoArquivo = false;
              swal.fire(
                `Relatório de envio`,
                `${arquivosSucesso} - ${arquivosErro}!`,
                'info'
              );
            }
          },
          error: (err) => {
            quantidadeDeEnvios++;
            enviado = quantidadeDeEnvios === quantidadeDeArquivos;
            this.dataSource = new MatTableDataSource([]);
            arquivosErro = arquivosErro + ' - ' + file.name;
            if (enviado) {
              this.enviandoArquivo = false;
              swal.fire(
                `Relatório de envio`,
                `${arquivosSucesso} - ${arquivosErro}!`,
                'info'
              );
            }
          },
          complete: () => {
            console.log('Enviar arquivos completo.');
          },
        });
      });
    } else {
      swal.fire(
        'Arquivo não selecionado',
        'Nenhum arquivo selecionado, por favor, revise e tente novamente',
        'info'
      );
    }
  }
}
