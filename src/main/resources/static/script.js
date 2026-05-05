
function listarCategorias(){
	fetch('/categoria/lista')
		.then(response => response.json())
		.then(data =>{
			const tbCategoria = document.querySelector('#tb-categoria tbody');
			tbCategoria.innerHTML = '';
			
			if(data.data.length === 0){
				tbCategoria.innerHTML = `
					<tr>
					    <td colspan="7" style="text-align:center;">
					        No hay datos
					    </td>
					</tr>
				`;
				return;
			};
			//console.log(typeof data, data);
			data.data.forEach(categoria =>{
				const tr = document.createElement('tr');
				tr.innerHTML = `
					<td>${categoria.id}</td>
					<td>${categoria.descripcion}</td>
					<td><button class="eliminar btn btn-danger" data-id="${categoria.id}">Eliminar</button></td>
					<td><button class="editar btn btn-warning" data-bs-toggle="modal" data-bs-target="#modalEditarCategoria" 
						data-id="${categoria.id}" data-descripcion="${categoria.descripcion}">
								Editar</button></td>
				`
				tbCategoria.appendChild(tr);
			})
			editarCategoria();
			eliminarCategoria();
		})
}

function cargarCategorias(selectId = 'select_cat', selectedId = null){
	const selectCat = document.getElementById(selectId);
	
	fetch('categoria/lista')
		.then(response => response.json())
		.then(data =>{
			selectCat.innerHTML = '<option value="" disabled selected>Elegir...</option>'
			data.data.forEach(categoria => {
				selectCat.add(new Option(categoria.descripcion, categoria.id));
			});
			if (selectedId) 
				selectCat.value = selectedId;
		})
}

formCategoria.addEventListener('submit', function(event){
	event.preventDefault();
	
	const descripcion = document.getElementById('descripcion').value;
	
	const categoria = {
		descripcion : descripcion
	}
	
	fetch('/categoria/crear',{
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(categoria)
	})
	.then(response => response.json())
	.then(data =>{
		if(data.success){
			mostrarMensaje(data.mensaje, true);
			listarCategorias();
			cargarCategorias();
			formCategoria.reset();
		}
	})
})

function editarCategoria(){
	document.querySelectorAll('.editar').forEach(button =>{
		button.addEventListener('click', function(){
			document.querySelector('input[name="id"]').value = this.getAttribute('data-id');
			document.querySelector('input[name="nombre"]').value = this.getAttribute('data-descripcion');	
		})
	})
	
	document.getElementById('actualizar').addEventListener('click', () =>{
		categoriaBody = {
			id: document.querySelector('input[name="id"]').value,
			descripcion: document.querySelector('input[name="nombre"]').value,
		}
		actualizarCategoria(categoriaBody);
	})
}

function actualizarCategoria(datos){
	const modal = document.getElementById('modalEditarCategoria');
	const modalInstance = bootstrap.Modal.getOrCreateInstance(modal);
	fetch(`/categoria/actualizar/${datos.id}`,{
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(datos)
	})
	.then(response => response.json())
	.then(data =>{
		if(data.success){
			mostrarMensaje(data.mensaje, true);
			modalInstance.hide();
			categoriasModificadas();
		}
	})
}

function eliminarCategoria(categoriaId, fila){
	//document.querySelectorAll('.eliminar').forEach(button =>{
		//button.addEventListener('click', function(){
			//const fila = this.parentElement.parentElement;
			//const categoriaId = this.getAttribute('data-id');
			//if (confirm('¿Seguro que desea eliminar esta categoria?')) {
				fetch(`/categoria/eliminar/${categoriaId}`, {
					method: 'DELETE'
				})
				.then(response => response.json())
				.then(data =>{
					if(data.success){
						mostrarMensaje(data.mensaje, false);
						animacionEliminar(fila, () =>{
							categoriasModificadas();
						});
					}
				})
			//}
		//})
	//})
}

document.addEventListener('DOMContentLoaded', function() {
	document.querySelector('#tb-categoria tbody').addEventListener('click', function(event) {
	  const btnEliminar = event.target.closest('.eliminar');
	
	  if (btnEliminar) {
	    const fila = btnEliminar.closest('tr');
	    const categoriaId = btnEliminar.dataset.id;
	
	    confirmar('¿Seguro que desea eliminar esta categoria?', () => {
	      eliminarCategoria(categoriaId, fila);
	    });
	  }
	});
	listarCategorias();
	
	document.querySelector('#tb-producto tbody').addEventListener('click', function(event) {
	  const btnEliminar = event.target.closest('.eliminar');

	  if (btnEliminar) {
	    const fila = btnEliminar.closest('tr');
	    const productoId = btnEliminar.dataset.id;

	    confirmar('¿Seguro que desea eliminar este producto?', () => {
	      eliminarProducto(productoId, fila);
	    });
	  }
	});
	listarProductos();
});

function listarProductos(){
	fetch('/producto/lista')
		.then(response => response.json())
		.then(data => {
			const tbProducto = document.querySelector('#tb-producto tbody')
			tbProducto.innerHTML = '';
			
			if(data.data.length === 0){
				tbProducto.innerHTML = `
					<tr>
					    <td colspan="7" style="text-align:center;">
					        No hay datos
					    </td>
					</tr>
				`;
				return;
			};
			data.data.forEach(producto =>{
				const precio = producto.precio.toFixed(2);
				const tr = document.createElement('tr');
				tr.innerHTML = `
					<td>${producto.id}</td>
					<td>${producto.nombre}</td>
					<td>S/${precio}</td>
					<td>${producto.categoriaResponseDto.descripcion}</td>
					<td><button class="eliminar btn btn-danger" data-id="${producto.id}">Eliminar</button></td>
					<td><button class="editar btn btn-warning" data-bs-toggle="modal" data-bs-target="#modalEditarProducto" 
						data-id="${producto.id}" data-nombre="${producto.nombre}" data-precio="${precio}" 
							data-categoriaId="${producto.categoriaResponseDto.id}" data-categoria="${producto.categoriaResponseDto.descripcion}">
								Editar</button></td>
				`
				tbProducto.appendChild(tr);
			})
			editarProducto();
		})
}

formProducto.addEventListener('submit', function(event){
	event.preventDefault();
	
	const nombre = document.getElementById('producto').value;
	const precio = document.getElementById('precio').value;
	const categoriaId = document.getElementById('select_cat').value;
	
	/*if (!categoriaId) {
	  mostrarMensaje('Debes seleccionar una categoría', false);
	  return;
	}*/
	
	const producto = {
		nombre : nombre,
		precio : precio,
		categoriaId : categoriaId
	}
	
	fetch('/producto/crear', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(producto)
	})
	.then(response => response.json())
	.then(data =>{
		if(data.success){
			mostrarMensaje(data.mensaje, true);
			listarProductos();
			formProducto.reset();
		}
	})
})

function editarProducto(){
	var selectCatId = 0;
	document.querySelectorAll('.editar').forEach(button =>{
		button.addEventListener('click', function(){
			document.querySelector('input[name="editProdId"]').value = this.getAttribute('data-id');
			document.querySelector('input[name="editNombre"]').value = this.getAttribute('data-nombre');
			document.querySelector('input[name="edtiPrecio"]').value = this.getAttribute('data-precio');
			selectCatId = this.getAttribute('data-categoriaId');
			cargarCategorias('select_cat_edit', selectCatId);
			/*console.log(this.getAttribute('data-id') + " " + this.getAttribute('data-nombre') + " " + 
			this.getAttribute('data-precio') + " " + this.getAttribute('data-categoriaId'));*/		
		})
	})

	document.getElementById('actualizarProducto').addEventListener('click', () =>{
		productoBody = {
			id: document.querySelector('input[name="editProdId"]').value,
			nombre: document.querySelector('input[name="editNombre"]').value,
			precio: document.querySelector('input[name="edtiPrecio"]').value,
			categoriaId: selectCatId,
		}
		console.log(productoBody.id + " " + productoBody.nombre + " " + productoBody.precio + " " + productoBody.categoriaId)
		actualizarProducto(productoBody);
	})
}

function actualizarProducto(datos){
	const modal = document.getElementById('modalEditarProducto');
	const modalInstance = bootstrap.Modal.getOrCreateInstance(modal);
	fetch(`/producto/actualizar/${datos.id}`,{
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(datos)
	})
	.then(response => response.json())
	.then(data => {
		if(data.success){
			mostrarMensaje(data.mensaje, true);
			modalInstance.hide();
			listarProductos();
		}
	})
}

function eliminarProducto(productoId, fila){
		fetch(`/categoria/eliminar/${productoId}`, {
			method: 'DELETE'
		})
		.then(response => response.json())
		.then(data =>{
			if(data.success){
				mostrarMensaje(data.mensaje, false);
				animacionEliminar(fila, () =>{
					categoriasModificadas();
				});
			}
	})
}

function mostrarMensaje(mensaje, esExito) {
    const alerta = document.getElementById('mensaje-alerta');
    const texto = document.getElementById('mensaje-texto');

    // limpia clases anteriores y agrega la que corresponde
    alerta.className = 'alert';
    alerta.classList.add(esExito ? 'alert-success' : 'alert-danger');
    alerta.classList.remove('d-none');
    texto.textContent = mensaje;

    // oculta después de 3 segundos
    setTimeout(() => {
        alerta.classList.add('d-none');
    }, 2000);
}

function animacionEliminar(fila, callback){
	fila.classList.add('animate__animated', 'animate__backOutRight');
	setTimeout(function () {
	    fila.remove();
		if(callback)
			callback();
	}, 1000);
}

function categoriasModificadas(){
	listarCategorias();
	cargarCategorias();
	listarProductos();
}

function confirmar(mensaje, onAceptar) {
  const modal = document.getElementById('modalConfirmar');
  const modalInstance = bootstrap.Modal.getOrCreateInstance(modal);

  document.getElementById('modalConfirmarMensaje').textContent = mensaje;

  //Reemplaza el listener anterior para evitar duplicados
  const btnAceptar = document.getElementById('btnConfirmarAceptar');
  const btnNuevo = btnAceptar.cloneNode(true);
  btnAceptar.replaceWith(btnNuevo);

  btnNuevo.addEventListener('click', () => {
    modalInstance.hide();
    onAceptar();
  });

  modalInstance.show();
}

cargarCategorias();
//listarProductos();
//listarCategorias();