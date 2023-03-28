package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController{

    @Autowired //injeção de depedência
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastrosMedico dados){
        //@RequestBody ler o corpo da requisição e mapear os campos dele para o DTO
        repository.save(new Medico(dados));

    }

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao){
        //size = 10, define que vai aparecer 10 items por página na lista | page = 0 define que ele começa a partir da pagina 0
        //sort = {"nome"} define que vai ser ordenado pelo nome
        //pageable é usado quando nao se quer retornar toda a lista, mas sim, alguma parte da lista, usando paginacao
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    //já atualiza no banco de dado de imediato, sem fazer nenhuma requisição
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }

    /*
    @GetMapping
    public List<DadosListagemMedico> listar(){
        //adiciona todos os medicos do bd em uma lista, obs: puxa apenas alguns dados especificado no DTO ListageMedicos
        return repository.findAll().stream().map(DadosListagemMedico::new).toList();
    }
    */
     
}
