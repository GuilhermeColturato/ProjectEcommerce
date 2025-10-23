import React, { useState, useEffect } from 'react';
import { api } from '../services/api.js';
import ProductForm from '../components/ProductForm.jsx';

const AdminPage = () => {
    const [products, setProducts] = useState([]);
    const [editingProduct, setEditingProduct] = useState(null);

    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const response = await api.get('/products');
                setProducts(response.content);
            } catch (error) {
                console.error('Error fetching products:', error);
                alert('Error fetching products');
            }
        };

        fetchProducts();
    }, []);

    const handleSave = async (product) => {
        try {
            if (product.id) {
                await api.put(`/products/${product.id}`, product);
            } else {
                await api.post('/products', product);
            }
            setEditingProduct(null);
            const response = await api.get('/products');
            setProducts(response.content);
            alert('Product saved successfully');
        } catch (error) {
            console.error('Error saving product:', error);
            alert('Error saving product');
        }
    };

    const handleDelete = async (productId) => {
        try {
            await api.delete(`/products/${productId}`);
            const response = await api.get('/products');
            setProducts(response.content);
            alert('Product deleted successfully');
        } catch (error) {
            console.error('Error deleting product:', error);
            alert('Error deleting product');
        }
    };

    return (
        <div>
            <h1>Admin Panel</h1>
            <button onClick={() => setEditingProduct({})}>Create Product</button>
            {editingProduct && (
                <ProductForm
                    product={editingProduct}
                    onSave={handleSave}
                    onCancel={() => setEditingProduct(null)}
                />
            )}
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {products.map((product) => (
                        <tr key={product.id}>
                            <td>{product.name}</td>
                            <td>{product.price}</td>
                            <td>
                                <button onClick={() => setEditingProduct(product)}>Edit</button>
                                <button onClick={() => handleDelete(product.id)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default AdminPage;
