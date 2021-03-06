//
// --------------------------------------------------------------------------
//  Gurux Ltd
// 
//
//
// Filename:        $HeadURL$
//
// Version:         $Revision$,
//                  $Date$
//                  $Author$
//
// Copyright (c) Gurux Ltd
//
//---------------------------------------------------------------------------
//
//  DESCRIPTION
//
// This file is a part of Gurux Device Framework.
//
// Gurux Device Framework is Open Source software; you can redistribute it
// and/or modify it under the terms of the GNU General Public License 
// as published by the Free Software Foundation; version 2 of the License.
// Gurux Device Framework is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of 
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
// See the GNU General Public License for more details.
//
// More information of Gurux products: http://www.gurux.org
//
// This code is licensed under the GNU General Public License v2. 
// Full text may be retrieved at http://www.gnu.org/licenses/gpl-2.0.txt
//---------------------------------------------------------------------------

package gurux.common;

/** 
 An argument class for media state changed event.
*/
public class MediaStateEventArgs
{
    private MediaState m_State;
    private boolean m_Accept;
    
    /** 
     Constructor
    */
    public MediaStateEventArgs()
    {
        setAccept(true);
    }

    /** 
     Constructor
    */
    public MediaStateEventArgs(MediaState state)
    {
        super();
        setState(state);
    }

    /** 
     Status information from media state.
    */
    
    public final MediaState getState()
    {
        return m_State;
    }
    public final void setState(MediaState value)
    {
        m_State = value;
    }

    /** 
     True is returned if media state change succeeded.
    */
    public final boolean getAccept()
    {
        return m_Accept;
    }
    public final void setAccept(boolean value)
    {
        m_Accept = value;
    }
}