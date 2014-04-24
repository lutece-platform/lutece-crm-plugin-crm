/*
 * Copyright (c) 2002-2014, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.crm.business.demand.category;

import fr.paris.lutece.test.LuteceTestCase;


/**
 *
 * CategoryTest
 *
 */
public class CategoryTest extends LuteceTestCase
{
    private static final String NAME1 = "Name1";
    private static final String NAME2 = "Name2";
    private static final String DESCRIPTION1 = "Description1";
    private static final String DESCRIPTION2 = "Description2";

    /**
     * Test business of class fr.paris.lutece.plugins.crm.business.demand.category.Category
     */
    public void testBusiness(  )
    {
        // Initialize an object
        Category category = new Category(  );
        category.setName( NAME1 );
        category.setDescription( DESCRIPTION1 );

        // Test create
        CategoryHome.create( category );

        Category categoryStored = CategoryHome.findByPrimaryKey( category.getIdCategory(  ) );
        assertEquals( category.getIdCategory(  ), categoryStored.getIdCategory(  ) );
        assertEquals( category.getName(  ), categoryStored.getName(  ) );
        assertEquals( category.getDescription(  ), categoryStored.getDescription(  ) );

        // Test update
        category.setName( NAME2 );
        category.setDescription( DESCRIPTION2 );
        CategoryHome.update( category );
        categoryStored = CategoryHome.findByPrimaryKey( category.getIdCategory(  ) );
        assertEquals( category.getIdCategory(  ), categoryStored.getIdCategory(  ) );
        assertEquals( category.getName(  ), categoryStored.getName(  ) );
        assertEquals( category.getDescription(  ), categoryStored.getDescription(  ) );

        // Test finders
        CategoryHome.findFirstCategory(  );
        CategoryHome.getCategories(  );
        CategoryHome.getCategoriesList(  );

        // Test remove
        CategoryHome.remove( category.getIdCategory(  ) );
        categoryStored = CategoryHome.findByPrimaryKey( category.getIdCategory(  ) );
        assertNull( categoryStored );
    }
}
