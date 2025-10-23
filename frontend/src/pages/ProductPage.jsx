import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { api } from '../services/api.js';

const ProductPage = () => {
    const { id } = useParams();
    const [product, setProduct] = useState(null);
    const [quantity, setQuantity] = useState(1);
    const userId = localStorage.getItem('userId') || 'demo-user';
    localStorage.setItem('userId', userId);

    useEffect(() => {
        const fetchProduct = async () => {
            try {
                const response = await api.get(`/products/${id}`);
                setProduct(response);
            } catch (error) {
                console.error('Error fetching product:', error);
                alert('Error fetching product');
            }
        };

        fetchProduct();
    }, [id]);

    const addToCart = async () => {
        try {
            await api.post('/cart/items', { userId, productId: id, quantity });
            alert('Product added to cart');
        } catch (error) {
            console.error('Error adding to cart:', error);
            alert('Error adding to cart');
        }
    };

    if (!product) {
        return <div>Loading...</div>;
    }

    return (
        <div>
            <h1>{product.name}</h1>
            <p>{product.description}</p>
            <p>Price: {product.price}</p>
            <input
                type="number"
                value={quantity}
                onChange={(e) => setQuantity(parseInt(e.target.value))}
                min="1"
            />
            <button onClick={addToCart}>Add to Cart</button>
        </div>
    );
};

export default ProductPage;
